package org.example.syntheakds.processing

import com.fasterxml.jackson.databind.JsonNode
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.hl7.fhir.r4.model.Address
import org.hl7.fhir.r4.model.BooleanType
import org.hl7.fhir.r4.model.CodeableConcept
import org.hl7.fhir.r4.model.Coding
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.Identifier
import org.hl7.fhir.r4.model.MedicationRequest
import org.hl7.fhir.r4.model.Narrative
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Resource
import org.hl7.fhir.r4.model.StringType
import org.hl7.fhir.r4.model.codesystems.AdministrativeGender

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

        return null
    }

    static Observation convertObservation(JsonNode observationNode){
        return null
    }

    static MedicationRequest convertMedicationRequest(JsonNode medicationNode){
        return null
    }

}
