package org.example.syntheakds.processing

import com.fasterxml.jackson.databind.ObjectMapper

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.util.function.Consumer

class ProcessingTask implements Consumer<Path> {

    private static final ObjectMapper objectMapper = new ObjectMapper()

    @Override
    void accept(Path path) {
        //Read whole file into memory
        def content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8)
        def bundleRoot = objectMapper.readTree(content)

    }
}
