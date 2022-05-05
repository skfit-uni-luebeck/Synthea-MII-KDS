package org.example.syntheakds.processing

import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.util.function.Consumer

class ProgressTask implements Consumer<Queue<Object>> {

    private static final Logger logger = LogManager.getLogger(ProgressTask.class)

    @Override
    void accept(Queue<Object> q) {
        def factor = 50/q.size() //Bar length divided by initial queue size
        do {
            def remaining = 0
            if (q.size() > 0) remaining = Math.max(Math.floor(q.size()*factor) as int, 0)
            def bar = "#"*(50 - remaining) + " "*remaining
            print("Progress: [${bar}]\r")
            Thread.sleep(250)
        } while(q.size() > 0)
        print("Progress: [${"#"*50}]\r")
    }
}
