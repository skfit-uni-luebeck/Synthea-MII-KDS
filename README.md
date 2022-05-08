# SyntheaKDS

This project allows for the generation of synthetic patient data using the patient data generator [Synthea](https://github.com/synthetichealth/synthea).
Generated data is transformed in order to conform to the [KDS Profiles](https://simplifier.net/organization/koordinationsstellemii/~home)
provided by the Medical Informatics Initiative (MII). Provided below is the list of profiles used for each resource type
in the original data:

- [Patient](https://simplifier.net/medizininformatikinitiative-modulperson/sdmiipersonpatient) - 2.0.0-ballot2
- [Observation - Vitalsigns](https://simplifier.net/medizininformatikinitiative-modulperson/sdmiipersonvitalstatus) - 2.0.0-ballot2
- [Observation - Laboratory](https://simplifier.net/medizininformatikinitiative-modullabor/observationlab) - 1.0
- [Condition](https://simplifier.net/medizininformatikinitiative-moduldiagnosen/diagnose) - 2.0.0
- [Procedure](https://simplifier.net/medizininformatikinitiative-modulprozeduren/sd_mii_prozedur_procedure) - 2.0.0-ballot2
- [Encounter](https://simplifier.net/medizininformatikinitiative-modulfall/kontaktgesundheitseinrichtung) - 2.0.0
- [DiagnosticReport](https://simplifier.net/medizininformatikinitiative-modullabor/diagnosticreportlab) - 1.0
- [MedicationAdministration](https://simplifier.net/medizininformatikinitiative-modulmedikation/profilemedicationadministrationmedikation) - 1.0.11
- [MedicationRequest](https://simplifier.net/medizininformatikinitiative-modulmedikation/profilemedicationrequestmedikamentenverordnung) - 0.1.0

Depending on the type of the Observation instance the different profiles are applied. Note that the synthetic data
provides many more Resource instance of other types. However, since fitting profiles could not be identified they are
not converted. 
Synthea uses mostly SNOMED CT as a coding system for many values. This is not the case for information related to
prescribed medications which is encoded using the RxNorm coding system of the NIH. Such codes are converted to ATC codes
using the RxNAV browser [RxNorm-API](https://lhncbc.nlm.nih.gov/RxNav/APIs/RxNormAPIs.html). If suitable codes are found
the corresponding resource is converted.

# Parameters:

- **int**: number of patients to be generated