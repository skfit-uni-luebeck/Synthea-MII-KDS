package org.example.syntheakds.processing.openehr

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.parser.IParser
import com.fasterxml.jackson.databind.JsonNode
import com.nedap.archie.rm.datatypes.CodePhrase
import com.nedap.archie.rm.datavalues.DvCodedText
import com.nedap.archie.rm.support.identification.TerminologyId
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.ehrbase.client.classgenerator.shareddefinition.Language
import org.example.syntheakds.processing.openehr.generated.kdsdiagnosecomposition.KDSDiagnoseComposition
import org.example.syntheakds.processing.openehr.generated.kdsdiagnosecomposition.definition.PrimaercodeEvaluation
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.KDSLaborberichtComposition
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.ErgebnisStatusDefiningCode
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.LaborbefundObservation
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.LabortestKategorieDefiningCode
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.ProLaboranalytCluster
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.ProLaboranalytMesswertDvCodedText
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.ProLaboranalytMesswertDvQuantity
import org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition.ProLaboranalytMesswertElement
import org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.KDSPersonComposition
import org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.definition.GeschlechtEvaluation
import org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.KDSProzedurComposition

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity
import org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.definition.ProzedurAction
import org.hl7.fhir.r4.model.Coding
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Procedure
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class OpenEhrConverter {

    private static final Logger logger = LogManager.getLogger(OpenEhrConverter.class)

    private static final FhirContext ctx = FhirContext.forR4()

    private static final IParser parser = ctx.newJsonParser()

    static CompositionEntity convert(JsonNode resourceNode) {
        switch (resourceNode.get("resourceType").asText()) {
            case "Patient":
                return convertPatient(resourceNode)
            case "Condition":
                return convertCondition(resourceNode)
            case "Observation":
                return convertObservation(resourceNode)
            case "Procedure":
                return convertProcedure(resourceNode)
            default:
                logger.debug("[!]No handling available for resource of type ${resourceNode.get("resourceType")}")
                return null
        }
    }

    static KDSPersonComposition convertPatient(JsonNode resourceNode) {
        Patient patient = parser.parseResource(Patient.class, resourceNode.toString())
        return new KDSPersonComposition().tap {
            it.setGeschlecht(new GeschlechtEvaluation().tap {
                it.setAdministrativesGeschlecht(convertGender(patient.getGender()))
            })
        }
    }

    static KDSDiagnoseComposition convertCondition(JsonNode resourceNode) {
        Condition condition = parser.parseResource(Condition.class, resourceNode.toString())
        return new KDSDiagnoseComposition().tap {
            it.setLanguage(Language.DE)
            it.setPrimaercode(List.of(new PrimaercodeEvaluation().tap {
                it.setKodierteDiagnose(convertCoding(condition.getCode().getCoding().first()))
                it.setFeststellungsdatumValue(convertFHIRDateTimeString(condition.getRecordedDateElement().toHumanDisplay()))
            }))
        }
    }

    static KDSLaborberichtComposition convertObservation(JsonNode resourceNode) {
        Observation observation = parser.parseResource(Observation.class, resourceNode.toString())
        return new KDSLaborberichtComposition().tap {
            it.setLaborbefund(new LaborbefundObservation().tap {
                it.setLabortestKategorieDefiningCode(LabortestKategorieDefiningCode.LABORATORY)
                it.setProLaboranalyt(List.of(new ProLaboranalytCluster().tap {
                    it.setErgebnisStatusDefiningCode(ErgebnisStatusDefiningCode.ENDBEFUND)
                    it.setBezeichnungDesAnalyts(convertCoding(observation.getCode().getCoding().first()))

                    if (observation.hasValueQuantity()) {
                        it.setMesswert(List.of(new ProLaboranalytMesswertElement().tap() {
                            it.setValue2(new ProLaboranalytMesswertDvQuantity().tap {
                                it.setMesswertMagnitude(observation.getValueQuantity().getValue())
                                it.setMesswertUnits(observation.getValueQuantity().getCode())
                            })
                        }))
                    } else if (observation.hasValueCodeableConcept()) {
                        it.setMesswert(List.of(new ProLaboranalytMesswertElement().tap() {
                            it.setValue2(new ProLaboranalytMesswertDvCodedText().tap {
                                it.setMesswert(convertCoding(observation.getValueCodeableConcept().getCoding().first()))
                            })
                        }))
                    }
                }))
            })
        }
    }

    static KDSProzedurComposition convertProcedure(JsonNode resourceNode) {
        Procedure procedure = parser.parseResource(Procedure.class, resourceNode.toString())
        return new KDSProzedurComposition().tap {
            it.setProzedur(new ProzedurAction().tap {
                it.setNameDerProzedur(convertCoding(procedure.getCode().getCoding().first()))
            })
        }
    }

    static DvCodedText convertCoding(Coding coding) {
        return new DvCodedText(coding.getDisplay(), new CodePhrase(new TerminologyId(coding.getSystem()), coding.getCode(), coding.getDisplay()))
    }

    static DvCodedText convertGender(AdministrativeGender gender) {
        return new DvCodedText(gender.getDisplay(), new CodePhrase(new TerminologyId(gender.getSystem()), gender.toCode(), gender.getDisplay()))
    }

    static LocalDateTime convertFHIRDateTimeString(String dateTimeStr) {
        def formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm:ss")
        return LocalDateTime.parse(dateTimeStr, formatter)
    }

}
