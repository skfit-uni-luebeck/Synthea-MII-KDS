package org.example.syntheakds.config

import java.nio.file.Path
import java.nio.file.Paths

class SyntheaKDSConfig {

    //Preset values
    static final Path basePath = Paths.get("").toAbsolutePath()
    static final Path outputDirPath = basePath.resolve(Paths.get("output", System.currentTimeMillis().toString()))
    static final Path tmpDirPath = outputDirPath.resolve('tmp_output')
    static final Path patDirPath = tmpDirPath.resolve('fhir')
    static final Path kdsDirPath = outputDirPath.resolve('kds')

    //Adjustable values
    static int patientCount = 100

}
