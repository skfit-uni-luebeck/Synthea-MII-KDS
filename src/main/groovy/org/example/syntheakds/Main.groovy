package org.example.syntheakds

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.LoggerContext
import org.example.syntheakds.config.SyntheaKDSConfig
import org.example.syntheakds.read.DataProvider
import org.mitre.synthea.engine.Generator
import org.mitre.synthea.engine.Generator.GeneratorOptions
import org.mitre.synthea.helpers.Config

import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

class Main {

    private static final Logger logger = LogManager.getLogger(Main.class)

    static void main(String[] args){

        configureLog4j2()

        logger.info("[#]Starting application ...")

        logger.info("[#]Running Synthea ...")
        def options = configureGeneratorOptions()
        def generator = new Generator(options)
        generator.run()
        logger.info("[#]Finished running Synthea.")

        logger.info("[#]starting conversion ...")
        def ioPool = Executors.newFixedThreadPool(50)
        def provider = new DataProvider(SyntheaKDSConfig.patDir, 100, 10000, ioPool as ThreadPoolExecutor)

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

    /**
     * Method for setting Log4j2 config file location. Should work in jar as well
     */
    private static void configureLog4j2(){
        def configFilePath = Paths.get('config', 'log4j2.xml').toString()
        def configFileUrl = Thread.currentThread().getContextClassLoader().getResource(configFilePath)
        def context = LogManager.getContext(false) as LoggerContext
        context.setConfigLocation(configFileUrl.toURI())
    }

}
