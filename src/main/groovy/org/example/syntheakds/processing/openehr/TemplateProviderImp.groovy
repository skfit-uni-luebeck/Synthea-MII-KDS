package org.example.syntheakds.processing.openehr

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.apache.xmlbeans.XmlException
import org.ehrbase.webtemplate.templateprovider.TemplateProvider
import org.openehr.schemas.v1.OPERATIONALTEMPLATE
import org.openehr.schemas.v1.TemplateDocument

import java.nio.charset.StandardCharsets

class TemplateProviderImp implements TemplateProvider{

    private static final Logger logger = LogManager.getLogger(TemplateProviderImp.class)

    @Override
    Optional<OPERATIONALTEMPLATE> find(String templateId) {
        return Optional.ofNullable(OperationalTemplateData.findByTemplateId(toUtf8Encoding(templateId)))
                .map({it -> it.getStream() })
                .map({InputStream stream ->
                    try{
                        return TemplateDocument.Factory.parse(stream)
                    }
                    catch (XmlException | IOException exc){
                        throw new RuntimeException(exc.getMessage(), exc)
                    }
                })
                .map({ TemplateDocument it -> it.getTemplate() }) as Optional<OPERATIONALTEMPLATE>
    }

    /**
     * Converts a given string to UTF-8 encoding since unfortunately the ids of the openEHR templates contain unique
     * german characters which cause issues if they are converted to other characters prior to comparison
     * @param string non UTF-8 encoded string
     * @return UTF-8 encoded string
     */
    private static String toUtf8Encoding(String string){
        return new String(string.getBytes(), StandardCharsets.UTF_8)
    }

    List<String> listTemplateIds(){
        def ids = []
        OperationalTemplateData.values().each { value ->
            ids << value.getTemplateId()
        }
        return ids
    }

}
