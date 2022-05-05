package org.example.syntheakds.config

import ca.uhn.fhir.context.FhirContext
import org.example.syntheakds.utils.Utils

import java.nio.file.Path
import java.nio.file.Paths

class SyntheaKDSConfig {

    //Preset values
    static final Path mapDirPath = Utils.getJarDir().resolve("map")
    static final Path basePath = Paths.get("").toAbsolutePath()
    static final Path outputDirPath = basePath.resolve(Paths.get("output", System.currentTimeMillis().toString()))
    static final Path tmpDirPath = outputDirPath.resolve('tmp_output')
    static final Path patDirPath = tmpDirPath.resolve('fhir')
    static final Path kdsDirPath = outputDirPath.resolve('kds')
    static final FhirContext ctx = FhirContext.forR4()

    //Adjustable values
    static int patientCount = 10000

}
