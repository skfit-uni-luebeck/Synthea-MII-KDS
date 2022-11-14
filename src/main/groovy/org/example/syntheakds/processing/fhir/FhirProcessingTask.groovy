package org.example.syntheakds.processing.fhir

import ca.uhn.fhir.parser.IParser
import com.fasterxml.jackson.databind.ObjectMapper
import org.example.syntheakds.config.SyntheaKDSConfig
import org.example.syntheakds.utils.Utils

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer

class FhirProcessingTask implements Consumer<Path> {

    private static final ObjectMapper objectMapper = new ObjectMapper()
    private static final IParser parser = SyntheaKDSConfig.ctx.newJsonParser().setPrettyPrint(true)

    @Override
    void accept(Path path) {
        //Read whole file into memory
        def content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
        def bundleEntry = objectMapper.readTree(content).get("entry")

        //Process content
        def instances = []
        bundleEntry.each {entry ->
            def resource = entry.get("resource")
            instances << FhirConverter.convert(resource)
        }

        //Create and write bundle
        def bundle = FhirUtils.createBundle(instances)
        def json = parser.encodeResourceToString(bundle)
        Utils.writeFile(json, SyntheaKDSConfig.fhirKdsDirPath, path.getFileName().toString())
    }
}
