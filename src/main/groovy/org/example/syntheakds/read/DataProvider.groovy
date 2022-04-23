package org.example.syntheakds.read

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.io.FileType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.nio.file.Path
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor

class DataProvider {

    private static final Logger logger = LogManager.getLogger(DataProvider.class)

    private final Path toDir
    private final int patientCount
    private final ObjectMapper objectMapper
    private final Queue<JsonNode> readQueue
    private final ThreadPoolExecutor ioPool

    DataProvider(Path toDir, int patientCount, int qCapacity, ThreadPoolExecutor ioPool){
        logger.debug("Initializing DataProvider instance ...")
        this.toDir = toDir
        this.patientCount = patientCount
        this.objectMapper = new ObjectMapper()
        this.readQueue = new ArrayBlockingQueue<>(qCapacity)
        this.ioPool = ioPool
        run()
    }

    private void run(){
        def files = this.findFiles()
        logger.debug("Reading files ...")
        files.each {file ->
            logger.debug("File @ ${file.getPath()}")
            this.ioPool.submit({this.readQueue.add(objectMapper.readTree(file))})
            logger.debug("--Submitted to I/O pool")
            logger.debug("--Task count: ${this.ioPool.taskCount}")
        }
    }

    Queue<JsonNode> getQ(){
        return this.readQueue
    }

    private List<File> findFiles(){
        logger.debug("Searching for files ...")
        def files = []
        this.toDir.eachFileMatch(type: FileType.FILES, nameFilter: ~/\*.json/){ file ->
            logger.debug("Found file @ ${file.toString()}")
            files << file.toFile()
        }
        return files
    }



}
