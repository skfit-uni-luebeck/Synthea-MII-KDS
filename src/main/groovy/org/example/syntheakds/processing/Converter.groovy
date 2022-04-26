package org.example.syntheakds.processing

import com.fasterxml.jackson.databind.JsonNode
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.hl7.fhir.r4.model.CodeableConcept
import org.hl7.fhir.r4.model.Coding
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.Enumerations
import org.hl7.fhir.r4.model.HumanName
import org.hl7.fhir.r4.model.Identifier
import org.hl7.fhir.r4.model.MedicationRequest
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
            it.setId(id)
            def idType = new CodeableConcept().addCoding(
                    new Coding("http://terminology.hl7.org/CodeSystem/v2-0203", "MR", "Medical Record Number")
            )
            it.addIdentifier(new Identifier().setUse(Identifier.IdentifierUse.OFFICIAL).setType(idType)
                    .setSystem(""/*TODO*/).setValue(id))

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


        }
    }

    static Condition convertCondition(JsonNode conditionNode){
        return null
    }

    static Observation convertObservation(JsonNode observatioNode){
        return null
    }

    static MedicationRequest convertMedicationRequest(JsonNode medicationNode){
        return null
    }

}
