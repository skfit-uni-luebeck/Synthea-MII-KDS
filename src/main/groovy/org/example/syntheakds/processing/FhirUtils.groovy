package org.example.syntheakds.processing

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.Condition
import org.hl7.fhir.r4.model.DiagnosticReport
import org.hl7.fhir.r4.model.Encounter
import org.hl7.fhir.r4.model.MedicationAdministration
import org.hl7.fhir.r4.model.MedicationRequest
import org.hl7.fhir.r4.model.Observation
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.Procedure
import org.hl7.fhir.r4.model.Resource

class FhirUtils {

    private static final Logger logger = LogManager.getLogger(FhirUtils.class)

    static Bundle createBundle(List<Resource> resources){
        def bundle = new Bundle()
        bundle.setType(Bundle.BundleType.TRANSACTION)

        resources.each {r ->
            switch (r?.fhirType()){
                case "Patient":
                    addPatientToBundle(r as Patient, bundle)
                    break
                case "Condition":
                    addConditionToBundle(r as Condition, bundle)
                    break
                case "Observation":
                    addObservationToBundle(r as Observation, bundle)
                    break
                case "Procedure":
                    addProcedureToBundle(r as Procedure, bundle)
                    break
                case "MedicationRequest":
                    addMedicationRequestToBundle(r as MedicationRequest, bundle)
                    break
                case "Encounter":
                    addEncounterToBundle(r as Encounter, bundle)
                    break
                case "DiagnosticReport":
                    addDiagnosticReportToBundle(r as DiagnosticReport, bundle)
                    break
                case "MedicationAdministration":
                    addMedicationAdministration(r as MedicationAdministration, bundle)
                    break
                case null:
                    break
                default:
                    logger.warn("[!]Resource of type <<${r.fhirType()}>> couldn't be handled!")
            }
        }

        return bundle
    }

    private static void addPatientToBundle(Patient patient, Bundle bundle){
        addToBundle(patient, "Patient", bundle)
    }

    private static void addConditionToBundle(Condition condition, Bundle bundle){
        addToBundle(condition, "Condition", bundle)
    }

    private static void addObservationToBundle(Observation observation, Bundle bundle){
        addToBundle(observation, "Observation", bundle)
    }

    private static void addProcedureToBundle(Procedure procedure, Bundle bundle){
        addToBundle(procedure, "Procedure", bundle)
    }

    private static void addMedicationRequestToBundle(MedicationRequest medicationRequest, Bundle bundle){
        addToBundle(medicationRequest, "MedicationRequest", bundle)
    }

    private static void addEncounterToBundle(Encounter encounter, Bundle bundle){
        addToBundle(encounter, "Encounter", bundle)
    }

    private static void addDiagnosticReportToBundle(DiagnosticReport diagReport, Bundle bundle){
        addToBundle(diagReport, "DiagnosticReport", bundle)
    }

    private static void addMedicationAdministration(MedicationAdministration medAdm, Bundle bundle){
        addToBundle(medAdm, "MedicationAdministration", bundle)
    }

    private static void addToBundle(Resource resource, String resourceType, Bundle bundle){
        bundle.addEntry()
                .setFullUrl("${resourceType}/" + resource.getIdElement().getValue())
                .setResource(resource)
                .getRequest()
                .setUrl(resourceType)
                .setMethod(Bundle.HTTPVerb.POST)
    }

}
