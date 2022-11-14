package org.example.syntheakds.processing.openehr

import com.fasterxml.jackson.databind.ObjectMapper
import com.nedap.archie.rm.composition.Composition
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity
import org.ehrbase.client.flattener.Unflattener
import org.ehrbase.serialisation.jsonencoding.CanonicalJson
import org.example.syntheakds.config.SyntheaKDSConfig
import org.example.syntheakds.utils.Utils

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer

class OpenEHRProcessingTask implements Consumer<Path> {
    def objectMapper = new ObjectMapper()
    def provider = new TemplateProviderImp()
    def unflattener = new Unflattener(provider)
    def json_marshaller = new CanonicalJson()

    @Override
    void accept(Path path) {
        //Read whole file into memory
        def content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
        def bundleEntry = objectMapper.readTree(content).get("entry")

        //Process content
        def compositions = []
        def patientHealthRecord = new HashMap<String, ArrayList>()
        bundleEntry.each { entry ->
            def resource = entry.get("resource")
            def composition = OpenEhrConverter.convert(resource)
            if (composition) {
                compositions << composition
                if (resource.get("resourceType").asText() == "Patient") {
                    patientHealthRecord[resource.get("id").asText()] = new ArrayList<CompositionEntity>()
                } else {
                    patientHealthRecord[resource.get("subject").get("reference").asText().replace("urn:uuid:", "")].add(composition)
                }
            }
        }

        //Create and write composition
        patientHealthRecord.each { entry ->
            def patientId = entry.getKey()
            def records = entry.getValue()
            def recordsByTemplateId = new HashMap<String, ArrayList<String>>()
            records.each { composition ->
                def unflattenedComposition = unflattener.unflatten(composition) as Composition
                def json = json_marshaller.marshal(unflattenedComposition)
                recordsByTemplateId.computeIfAbsent(unflattenedComposition.getArchetypeDetails().getTemplateId().toString(), k -> new ArrayList<>()).add(json)
            }
            recordsByTemplateId.each {it ->
                def templateId = it.getKey()
                def recordsJson = it.getValue()
                Utils.writeFile(recordsJson.toString(), SyntheaKDSConfig.openEHRKdsDirPath, "${patientId}/${templateId}.json")
            }
        }


    }
}
