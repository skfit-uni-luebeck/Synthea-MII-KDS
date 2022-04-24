package org.example.syntheakds.utils

import groovy.io.FileType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.appender.rolling.FileExtension

import java.nio.file.Path
import java.nio.file.Paths

class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class)

    private static final ClassLoader cLoader = Thread.currentThread().getContextClassLoader()

    static InputStream findResource(Path path){
        def stringPath = path.toString()
        logger.debug("[?]Searching for resource @ ${stringPath} ...")
        def stream = cLoader.getResourceAsStream(stringPath)
        if(stream) logger.warn("[!]Could not find resource @ ${stringPath}!")
        return stream
    }

    static URL findResourceURL(Path path){
        def stringPath = path.toString()
        logger.debug("[?]Searching for resource @ ${stringPath} ...")
        def url = cLoader.getResource(stringPath)
        if(url) logger.warn("[!]Could not find resource @ ${stringPath}!")
        return url
    }

    static URI findResourceURI(Path path){
        return findResourceURL(path).toURI()
    }

    static List<Path> findFilesInDir(Path dirPath, FileExtension fileExt){
        def fileList = new ArrayList<Path>()
        def dir = dirPath.toFile()
        dir.eachFileMatch(FileType.FILES, ~"^.*\\.${fileExt.toString()}\$"){
            file -> fileList << file.toPath()
                logger.debug("Found ${fileExt.toString().toUpperCase()} file: '${file.path}'")
        }
        return fileList
    }

    enum FileExtension{
        JSON("json")

        final String ext

        FileExtension(String ext){
            this.ext = ext
        }

        @Override
        String toString() {
            return this.ext
        }
    }

}
