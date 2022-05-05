package org.example.syntheakds.processing.rxnorm

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.TextNode
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.example.syntheakds.config.SyntheaKDSConfig
import org.example.syntheakds.utils.Utils

import java.nio.file.Path
import java.nio.file.Paths

class RxNormTranslator {

    private static final Logger logger = LogManager.getLogger(RxNormTranslator.class)

    private static final String baseUrl = "https://rxnav.nlm.nih.gov/REST/rxcui/"
    private static final Path mapPath = SyntheaKDSConfig.mapDirPath.resolve(Paths.get("rxnorm", "mapping_atc.json"))
    private static final ObjectMapper objectMapper = new ObjectMapper()
    private static final cachedAnswers = loadCache()

    List<String> translate(String rxnormCode){
        def atcCodes = cachedAnswers.get(rxnormCode)
        if(atcCodes == null){
            atcCodes = lookupCode(rxnormCode)
            cachedAnswers[rxnormCode] = atcCodes
            return atcCodes
        }
        else if(atcCodes.size() == 0){
            return []
        }
        else{
            return atcCodes
        }
    }

    private static HashMap<String, List<String>> loadCache(){
        def root
        def file = mapPath.toFile()
        if(file != null && file.exists()){
            root = objectMapper.readTree(file)
        }
        else{
            def stream = Utils.findResource(Path.of("rxnorm", "mapping_atc.json"))
            root = objectMapper.readTree(stream)
            file.toPath().getParent().toFile().mkdirs()
        }

        def map = new HashMap<String, List<String>>()

        root.get("codes").each {codeNode ->
            map[codeNode.get("rxnorm").asText()] = codeNode.get("atc").collect(c -> c.asText())
        }

        //Ensure newly looked up codes are added by writing them into the cache file
        Runtime.getRuntime().addShutdownHook({
            def listNode = objectMapper.createArrayNode()
            def cacheRoot = objectMapper.createObjectNode().set("codes", listNode)
            cachedAnswers.entrySet().each {entry ->
                def atcNode = objectMapper.createArrayNode()
                def entryNode = objectMapper.createObjectNode().tap {
                    set("rxnorm", new TextNode(entry.key))
                    set("atc", atcNode)
                }
                entry.value.each {v ->
                    atcNode.add(v)
                }
                listNode.add(entryNode)
            }
            file.text = ""
            def writer = objectMapper.writer(new DefaultPrettyPrinter())
            writer.writeValue(file, cacheRoot)
        })

        return map
    }

    private static List<String> lookupCode(String rxnormCode){
        def getIngredients = new URL(baseUrl + "${rxnormCode}/related.json?tty=IN&tty=MIN").openConnection()
        def json = objectMapper.readTree(getIngredients.inputStream)
        def atcList = []

        //RxCUIs
        def conceptGroup = json.get("relatedGroup").get("conceptGroup")
        conceptGroup.each {cg ->
            cg.get("conceptProperties")?.each {cp ->
                def getAtcCodes = new URL(baseUrl + "${cp.get("rxcui").asText()}/property.json?propName=ATC").openConnection()
                def atcJson = objectMapper.readTree(getAtcCodes.inputStream)

                //ATC Codes
                def propConcept = atcJson.get("propConceptGroup")?.get("propConcept")
                propConcept?.each {pc ->
                    atcList << pc.get("propValue").asText()
                }
            }
        }

        return atcList
    }

}

