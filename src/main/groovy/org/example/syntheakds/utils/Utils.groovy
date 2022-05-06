package org.example.syntheakds.utils

import groovy.io.FileType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.core.appender.rolling.FileExtension

import java.nio.file.Path
import java.nio.file.Paths
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Utils {

    private static final Logger logger = LogManager.getLogger(Utils.class)

    private static final ClassLoader cLoader = Thread.currentThread().getContextClassLoader()
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
    private static DateTimeFormatter formatterMicro = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")

    static InputStream findResource(Path path){
        def stringPath = path.toString()
        logger.debug("[?]Searching for resource @ ${stringPath} ...")
        def stream = cLoader.getResourceAsStream(stringPath)
        if(!stream) logger.warn("[!]Could not find resource @ ${stringPath}!")
        return stream
    }

    static URL findResourceURL(Path path){
        def stringPath = path.toString()
        logger.debug("[?]Searching for resource @ ${stringPath} ...")
        def url = cLoader.getResource(stringPath.replaceAll("\\\\", "/"))
        if(!url) logger.warn("[!]Could not find resource @ ${stringPath}!")
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

    static void writeFile(String content, Path outputPath, String fileName){
        def newFile = outputPath.resolve(fileName).toFile()
        newFile << content
    }

    static Path getJarDir(){
        return new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toPath()
    }

    /**
     * Method for converting dates in Synthea resources to Date instances
     * @param date String representing a date and time with offset
     * @return Date instance representing the date and time of the input string or null if the input is null
     */
    static Date dateFromSyntheaDate(String date){
        if(date == null){
            return null
        }
        else{
            OffsetDateTime syntheaDateTime = null
            try{
                syntheaDateTime = OffsetDateTime.parse(date, formatter)
            }
            catch (DateTimeParseException ignored){
                syntheaDateTime = OffsetDateTime.parse(date, formatterMicro)
            }
            return Date.from(syntheaDateTime.toInstant())
        }
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
