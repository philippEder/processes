package com.eder.engine.steps

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement
import com.eder.engine.model.Step

class LoggingStep(private val process: Process,
                  private val message: String): Step(flow = process, key = "Logging") {

    override fun execute(): ProcessElement.ProcessElementState {
        println(message)

        return ProcessElement.ProcessElementState.FINISHED
    }


}