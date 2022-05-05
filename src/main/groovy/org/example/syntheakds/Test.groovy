package org.example.syntheakds

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.syntheakds.processing.rxnorm.RxNormTranslator

class Test {

    static void main(String[] args){

        def cl = Thread.currentThread().getContextClassLoader()
        def r = cl.getResourceAsStream("rxnorm/mapping_atc.json")
        println r.readLines().join(" ")

    }

}
