package com.eder.engine.steps

import com.eder.engine.StepState
import com.eder.engine.Process

class LoggingStep(private val process: Process,
                  private val message: String): StepImpl(process) {

    override fun execute(): StepState {
        println(message)

        return StepState.FINISHED
    }

}