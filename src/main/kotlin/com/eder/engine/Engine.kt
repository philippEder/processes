package com.eder.engine



class Engine {

    fun proceed(process: Process) {
        visitEachStepList(process) { currentSteps ->
            process.currentSteps = currentSteps
            currentSteps.forEach {
                if (it.getPreviousStep() == null || StepState.FINISHED == it.getPreviousStep()?.getState()) {
                    it.setState(StepState.IN_PROGRESS)
                    val resultState = it.execute()
                    it.setState(resultState)
                }
            }
        }
    }

}