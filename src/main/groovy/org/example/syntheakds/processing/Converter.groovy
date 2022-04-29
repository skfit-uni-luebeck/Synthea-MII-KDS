package org.example.syntheakds.processing

import com.fasterxml.jackson.databind.JsonNode
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.example.syntheakds.utils.Utils
import org.hl7.fhir.r4.model.Address
import org.hl7.fhir.r4.model.BooleanType
import org.hl7.fhir.r4.model.CodeableConcept
import org.hl7.fhir.r4.model.Coding
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.Encounter
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.Identifier
import org.hl7.fhir.r4.model.Location
import org.hl7.fhir.r4.model.MedicationRequest
import org.hl7.fhir.r4.model.Narrative
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Period
import org.hl7.fhir.r4.model.Procedure
import org.hl7.fhir.r4.model.Quantity
import org.hl7.fhir.r4.model.Reference
import org.hl7.fhir.r4.model.Resource
import org.hl7.fhir.r4.model.StringType
import org.hl7.fhir.r4.model.codesystems.AdministrativeGender
import org.hl7.fhir.r4.model.codesystems.ObservationStatus

class Converter {

    private static final Logger logger = LogManager.getLogger(Converter.class)

    static Resource convert(JsonNode resourceNode){
        switch(resourceNode.get("resourceType").asText()){
            case "Patient":
                return convertPatient(resourceNode)
            case "Condition":
                return convertCondition(resourceNode)
            case "Observation":
                return  convertObservation(resourceNode)
            case "MedicationRequest":
                return convertMedicationRequest(resourceNode)
            default:
                logger.warn("[!]No handling available for resource of type ${resourceNode.get("resourceType")}")
                return null
        }
    }

    static Patient convertPatient(JsonNode patientNode){
        return new Patient().tap { it ->
            //Profile
            it.meta.addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-person/StructureDefinition/Patient")

            //Id and Identifier
            def id = patientNode.get("id").asText()
            def identifier = patientNode.get("identifier").get(1)
            it.setId(id)
            def idType = new CodeableConcept().addCoding(
                    new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MR", "Medical Record Number")
            )
            it.addIdentifier(new Identifier().setUse(Identifier.IdentifierUse.OFFICIAL).setType(idType)
                    .setSystem(identifier.get("system").asText()).setValue(identifier.get("value").asText()))

            //Name
            it.addName().tap {name ->
                def nameNode = patientNode.get("name").get(0)
                name.use = HumanName.NameUse.OFFICIAL
                name.family = nameNode.get("family").asText()
                name.given.addAll(nameNode.get("given").collect {givenNode -> new StringType(givenNode.asText())})
            }

            //Gender
            switch (patientNode.get("gender").asText()){
                case "female":
                    it.setGender(Enumerations.AdministrativeGender.FEMALE)
                    break
                case "male":
                    it.setGender(Enumerations.AdministrativeGender.MALE)
                    break
                default:
                    it.setGender(Enumerations.AdministrativeGender.UNKNOWN)
            }

            //Birth Date: YYYY-MM-DD
            def date = patientNode.get("birthDate").asText().split("-").collect {s ->Integer.parseInt(s)}
            //See https://docs.oracle.com/javase/8/docs/api/java/util/Date.html#Date-int-int-int-
            it.setBirthDate(new Date(date[0] - 1900, date[1] - 1, date[2]))

            //Address
            def address = patientNode.get("address").get(0)
            it.addAddress().setUse(Address.AddressUse.HOME).setType(Address.AddressType.BOTH)
                    .setLine(address.get("line").collect(line -> new StringType(line.asText())))
                    .setCity(address.get("city").asText()).setPostalCode(address.get("postalCode").asText())
                    .setCountry(address.get("country").asText())

            //Marital Status
            def maritalStatus = patientNode.get("maritalStatus").get("coding").get(0)
            it.setMaritalStatus(new CodeableConcept(new Coding(
                    maritalStatus.get("system").asText(),
                    maritalStatus.get("code").asText(),
                    maritalStatus.get("display").asText()
            )))

            //Multiple Birth
            it.setMultipleBirth(new BooleanType(patientNode.get("multipleBirth").asBoolean(false)))

            //Communication
            def language = patientNode.get("communication").get(0).get("language").get("coding").get(0)
            it.addCommunication().setLanguage(new CodeableConcept(new Coding(
                    language.get("system").asText(),
                    language.get("code").asText(),
                    language.get("display").asText()
            )))
        }
    }

    static Condition convertCondition(JsonNode conditionNode){
        return new Condition().tap {it ->
            //Profile
            it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-diagnose/StructureDefinition/Diagnose")

            //Id
            it.setId(conditionNode.get("id").asText())

            //Clinical Status
            def clinicalStatus = conditionNode.get("clinicalStatus").get("coding")
            it.setClinicalStatus(new CodeableConcept().tap {cc ->
                clinicalStatus.each {cs -> cc.addCoding(
                        new Coding()
                        .setSystem(cs.get("system").asText())
                        .setCode(cs.get("code").asText())
                )}
            })

            //Verification Status
            def verificationStatus = conditionNode.get("verificationStatus").get(0).get("coding")
            it.setVerificationStatus(new CodeableConcept().tap {cc ->
                verificationStatus.each {cs -> cc.addCoding(
                        new Coding()
                        .setSystem(cs.get("system").asText())
                        .setCode(cs.get("code").asText())
                )}
            })

            //Category
            def category = conditionNode.get("category").get(0).get("coding").each {c ->
                it.addCategory().addCoding(
                        new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
                )
            }

            //Code
            def code = conditionNode.get("code").get("coding")
            it.setCode(new CodeableConcept().tap {cc ->
                code.each {c -> new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())}
            })

            //Subject
            def subjectRef = conditionNode.get("subject").get("reference").asText()
            it.setSubject(new Reference(subjectRef))

            //Encounter
            def encounterRef = conditionNode.get("encounter").get("reference").asText()
            it.setEncounter(new Reference(encounterRef))

            //Date Times
            def onset = conditionNode.get("onsetDateTime").asText()
            def abatement = conditionNode.get("abatementDateTime").asText()
            def recorded = conditionNode.get("recordedDate").asText()
            it.setOnset(new StringType(onset))
            it.setAbatement(new StringType(abatement))
            it.setRecordedDate(Utils.dateFromSyntheaDate(recorded))
        }
    }

    static Observation convertObservation(JsonNode observationNode){
        return new Observation().tap {it ->
            //Id
            it.setId(observationNode.get("id").asText())

            //Category
            def category = observationNode.get("category").get(0).get("coding")
            it.addCategory(new CodeableConcept().tap {cc ->
                category.each {c ->
                    new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
                }
            })

            //Code
            def coding = observationNode.get("code").get("coding")
            it.setCode(new CodeableConcept().tap {cc ->
                cc.setCoding(coding.collect(c ->
                        new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
                ))
            })

            //Subject
            def subjectRef = observationNode.get("subject").get("reference").asText()
            it.setSubject(new Reference(subjectRef))

            //Encounter
            def encounterRef = observationNode.get("encounter").get("reference").asText()
            it.setEncounter(new Reference(encounterRef))

            //Effective
            def effective = observationNode.get("effectiveDateTime").asText()
            it.setEffective(new StringType(effective))

            //Issued
            def issued = observationNode.get("issued").asText()
            it.setIssued(Utils.dateFromSyntheaDate(issued))

            //Value: Notice that there are cases where neither are present (blood pressure)
            if(observationNode.has("valueCodeableConcept")){
                def value = observationNode.get("valueCodeableConcept").get("coding")
                it.setValue(new CodeableConcept().tap {cc ->
                    cc.setCoding(value.collect(c ->
                        new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
                    ))
                })
            }
            if (observationNode.has("valueQunatity")) {
                def value = observationNode.get("valueQuantity")
                it.setValue(
                        new Quantity()
                        .setValue(value.get("value").asDouble())
                        .setUnit(value.get("unit").asText())
                        .setSystem(value.get("system").asText())
                        .setCode(value.get("code").asText())
                )
            }

            //Possible components: can be present in surveys or blood pressure measurement for example
            def components = observationNode.get("component")
            handleSurveyComponent(components, it)

            switch (category.get(0).get("code").asText()){
                case "survey": //Survey and vital sign observations seem to fall into the capabilities of this profile
                case "vital-signs":
                    //Profile
                    it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-person/StructureDefinition/Vitalstatus")

                    //Status: Always has to be final!
                    it.setStatus(Observation.ObservationStatus.FINAL)
                    break
                case "laboratory":
                    //Profile
                    it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab")

                    //Status
                    def status = observationNode.get("status").asText()
                    it.setStatus(Observation.ObservationStatus.fromCode(status))
                    break
                default:
                    logger.warn("[!]Observation resource couldn't be matched to fitting category: code: ${category.get("code").asText()}")
            }
        }
    }

    private static void handleSurveyComponent(JsonNode componentNode, Observation it){
        it.setComponent(componentNode.collect(compNode -> new Observation.ObservationComponentComponent().tap {comp ->
            def code = compNode.get("code").get("coding")
            comp.setCode(new CodeableConcept().setCoding(code.collect(c ->
                new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
            )))

            if(compNode.has("valueCodeableConcept")){
                def value = compNode.get("valueCodeableConcept").get("coding")
                comp.setValue(new CodeableConcept().setCoding(value.collect(c ->
                    new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
                )))
            }
            if(compNode.has("valueQuantity")){
                def value = compNode.get("valueQuantity")
                comp.setValue(
                        new Quantity()
                        .setValue(value.get("value").asDouble())
                        .setUnit(value.get("unit").asText())
                        .setSystem(value.get("system").asText())
                        .setCode(value.get("code").asText())
                )
            }

            return comp
        }))
    }

    static Procedure convertProcedure(JsonNode procedureNode){
        return new Procedure().tap {it ->
            //Profile
            it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-prozedur/StructureDefinition/Procedure")

            //Id
            it.setId(procedureNode.get("id").asText())

            //Status
            def status = procedureNode.get("status").asText()
            it.setStatus(Procedure.ProcedureStatus.fromCode(status))

            //Code
            def code = procedureNode.get("code").get("coding")
            it.setCode(new CodeableConcept().setCoding(code.collect(c ->
                    new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
            )))

            //Subject
            def subjectRef = procedureNode.get("subject").get("reference").asText()
            it.setSubject(new Reference(subjectRef))

            //Encounter
            def encounterRef = procedureNode.get("encounter").get("reference").asText()
            it.setEncounter(new Reference(encounterRef))

            //Performed
            def performed = procedureNode.get("performedPeriod")
            it.setPerformed(
                    new Period()
                    .setStart(Utils.dateFromSyntheaDate(performed.get("start").asText()))
                    .setEnd(Utils.dateFromSyntheaDate(performed.get("end").asText()))
            )

            //Location
            def location = procedureNode.get("location")
            it.setLocation(
                    new Reference()
                    .setReference(location.get("reference").asText())
                    .setDisplay(location.get("display").asText())
            )
        }
    }

    static MedicationRequest convertMedicationRequest(JsonNode medicationNode){
        return new MedicationRequest().tap {it ->
            //Profile
            it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-medikation/StructureDefinition/MedicationRequest")

            //Id
            it.setId(medicationNode.get("id").asText())

            //Status
            def status = medicationNode.get("status").asText()
            it.setStatus(MedicationRequest.MedicationRequestStatus.fromCode(status))

            //Intent
            def intent = medicationNode.get("intent").asText()
            it.setIntent(MedicationRequest.MedicationRequestIntent.fromCode(intent))

            //Medication
            def medication = medicationNode.get("medicationCodeableConcept").get("coding")
            it.setMedication(new CodeableConcept().setCoding(medication.collect(c ->
                    new Coding(c.get("system").asText(), c.get("code").asText(), c.get("display").asText())
            )))

            //Subject
            def subjectRef = medicationNode.get("subject").get("reference").asText()
            it.setSubject(new Reference(subjectRef))

            //Encounter
            def encounterRef = medicationNode.get("encounter").get("reference").asText()
            it.setEncounter(new Reference(encounterRef))

            //AuthoredOn
            def authoredOn = medicationNode.get("authoredOn").asText()
            it.setAuthoredOn(Utils.dateFromSyntheaDate(authoredOn))

            //Requester
            def requester = medicationNode.get("requester")
            it.setRequester(
                    new Reference()
                    .setReference(requester.get("request").asText())
                    .setDisplay(requester.get("display").asText())
            )
        }
    }

    static Encounter convertEncounter(JsonNode encounterNode){
        return new Encounter().tap {it ->
            //Profile
            it.getMeta().addProfile("https://www.medizininformatik-initiative.de/fhir/core/modul-fall/StructureDefinition/KontaktGesundheitseinrichtung")

            //Id
            it.setId(encounterNode.get("id").asText())

            //Identifier
            def identifier = encounterNode.get("identifier")
            it.setIdentifier(identifier.collect(i ->
                    new Identifier()
                    .setUse(Identifier.IdentifierUse.fromCode(i.get("use").asText()))
                    .setSystem(i.get("system").asText())
                    .setValue(i.get("value").asText())
            ))

            //Status
            def status = encounterNode.get("status").asText()
            it.setStatus(Encounter.EncounterStatus.fromCode(status))

            //Class: the same coding system for encounter classes is used
            def encClass = encounterNode.get("class")
            it.setClass_(new Coding().setSystem(encClass.get("system").asText()).setCode(encClass.get("code").asText()))

            //Type
            def type = encounterNode.get("type").get(0).get("coding")
            it.addType(new CodeableConcept().setCoding(type.collect(c ->
                    new Coding(type.get("system").asText(), type.get("code").asText(), type.get("display").asText())
            )))

            //Subject
            def subjectRef = encounterNode.get("subject")
            it.setSubject(
                    new Reference()
                    .setReference(subjectRef.get("reference").asText())
                    .setDisplay(subjectRef.get("display").asText())
            )

            //Participant
            def participant = encounterNode.get("participant")
            it.setParticipant(participant.collect(p ->
                    new Encounter.EncounterParticipantComponent().tap {epc ->
                        epc.addType(new CodeableConcept().setCoding(p.get("type").get(0).get("coding").collect(c ->
                                new Coding()
                                        .setSystem(c.get("system").asText())
                                        .setCode(c.get("code").asText())
                                        .setDisplay(c.get("display").asText())
                        )))
                        def period = p.get("period")
                        epc.setPeriod(
                                new Period()
                                .setStart(Utils.dateFromSyntheaDate(period.get("start").asText()))
                                .setEnd(Utils.dateFromSyntheaDate(period.get("end").asText()))
                        )
                        def individual = p.get("individual")
                        epc.setIndividual(
                                new Reference()
                                .setReference(individual.get("reference").asText())
                                .setDisplay(individual.get("display").asText())
                        )
                    }
            ))

            //Period
            def period = encounterNode.get("period")
            it.setPeriod(
                    new Period()
                    .setStart(Utils.dateFromSyntheaDate(period.get("start").asText()))
                    .setEnd(Utils.dateFromSyntheaDate(period.get("end").asText()))
            )

            //Location
            def location = encounterNode.get("location")
            it.setLocation(location.collect(l ->
                    new Encounter.EncounterLocationComponent().tap {elc ->
                        def elcLocation = l.get("location")
                        elc.setLocation(
                                new Reference()
                                .setReference(elcLocation.get("reference").asText())
                                .setDisplay(elcLocation.get("display").asText())
                        )
                    }
            ))

            //Service provider
            def serviceProvider = encounterNode.get("serviceProvider")
            it.setServiceProvider(
                    new Reference()
                    .setReference(serviceProvider.get("reference").asText())
                    .setDisplay(serviceProvider.get("display").asText())
            )
        }
    }

}
