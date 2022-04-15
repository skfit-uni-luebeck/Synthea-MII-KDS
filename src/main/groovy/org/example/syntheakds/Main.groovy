package org.example.syntheakds

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.example.syntheakds.config.SyntheaKDSConfig
import org.mitre.synthea.engine.Generator
import org.mitre.synthea.engine.Generator.GeneratorOptions
import org.mitre.synthea.helpers.Config

import java.nio.file.Path

class Main {

    private static final Logger logger = LogManager.getLogger(Main.class)

    static void main(String[] args){

        logger.info("[#]Starting application ...")

        logger.info("[#]Running Synthea ...")
        def options = configureGeneratorOptions()
        def generator = new Generator(options)
        generator.run()
        logger.info("[#]Finished running Synthea.")

    }

    private static GeneratorOptions configureGeneratorOptions(){
        def options = new GeneratorOptions()
        options.population = SyntheaKDSConfig.patientCount
        Config.set("exporter.ccda.export", "false")
        Config.set("exporter.fhir.export", "true")
        Config.set("exporter.fhir_stu3.export", "false")
        Config.set("exporter.fhir_dstu2.export", "false")
        Config.set("exporter.subfolders_by_id_substring", "false")
        Config.set("exporter.baseDirectory", SyntheaKDSConfig.tmpOutputDir.toString())
        Config.set("generate.database_type", "none")
        return options
    }

}
