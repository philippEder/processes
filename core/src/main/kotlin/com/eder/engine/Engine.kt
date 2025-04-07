package com.eder.engine

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement.ProcessElementState.*


class Engine {

    fun proceed(process: Process) {
        process.visitEachElementList { currentSteps ->
            process.currentElements = currentSteps
            currentSteps.forEach {
                if (it.previous == null || FINISHED == it.previous?.state) {
                    it.state = IN_PROGRESS
                    val resultState = it.execute()
                    it.state = resultState
                }
            }
        }
    }

}