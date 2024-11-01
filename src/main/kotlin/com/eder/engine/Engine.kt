package com.eder.engine


class Engine {

    fun execute(process: Process) {
        visitEachStepList(process) { currentSteps ->
            process.currentSteps = currentSteps
            currentSteps.forEach {
                it.setState(StepState.IN_PROGRESS)
                val resultState = it.execute()
                it.setState(resultState)
            }
        }
    }

    fun proceed(process: Process) {
        visitEachStepList(process) { currentSteps ->
            process.currentSteps = currentSteps
            currentSteps.forEach {
                it.setState(StepState.IN_PROGRESS)
                val resultState = it.execute()
                it.setState(resultState)
            }
        }
    }

}