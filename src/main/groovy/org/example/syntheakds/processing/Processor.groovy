package org.example.syntheakds.processing

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class Processor<T> {

    private static final Logger logger = LogManager.getLogger(Processor.class)

    private final ObjectMapper objectMapper
    private final ThreadPoolExecutor pool
    private final List<T> items
    private final Consumer<T> task

    Processor(List<T> items, Consumer<T> task){
        this.objectMapper = new ObjectMapper()
        this.pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4)
        this.items = items
        this.task = task
    }

    void run(){
        logger.info("[#]Running processor ...")
        this.items.each {item ->
            this.pool.execute(() -> task(item))
        }

        this.pool.shutdown()

        try {
            this.pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)
        }
        catch (InterruptedException exc){
            logger.error("[!]Processor was interrupted while waiting for tasks to finish:\n${exc.getMessage()}")
        }
    }

}
