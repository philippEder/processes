package com.eder.engine

import com.eder.engine.model.Process
import com.eder.engine.model.Step.StepState


class Engine {

    fun proceed(process: Process) {
        process.visitEachStepList { currentSteps ->
            process.currentSteps = currentSteps
            currentSteps.forEach {
                if (it.previousStep == null || StepState.FINISHED == it.previousStep?.state) {
                    it.state = StepState.IN_PROGRESS
                    val resultState = it.execute()
                    it.state = resultState
                }
            }
        }
    }

}