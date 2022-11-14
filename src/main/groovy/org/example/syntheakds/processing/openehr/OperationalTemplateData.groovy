package org.example.syntheakds.processing.openehr

enum OperationalTemplateData {
    DIAGNOSE("KDS_Diagnose", "KDS_Diagnose.opt"),
    LABORBERICHT("KDS_Laborbericht", "KDS_Laborbericht.opt"),
    PERSON("KDS_Person", "KDS_Person.opt"),
    Prozedur("KDS_Prozedur", "KDS_Prozedur.opt"),

    private String templateId
    private String fileName

    OperationalTemplateData(String templateId, String fileName) {
        this.templateId = templateId
        this.fileName = fileName
    }

    InputStream getStream(){
        return getClass().getResourceAsStream("/openehr/opt/${fileName}")
    }

    static OperationalTemplateData findByTemplateId(String templateId){
        return Arrays.stream(values())
                .filter({ o -> (o.getTemplateId() == templateId) })
                .findAny()
                .orElse(null)
    }

    String getTemplateId() {
        return templateId
    }

    String getFileName() {
        return fileName
    }
}