package com.eder.engine

interface Step {



    fun getWorkflow(): Process
    fun getState(): StepState
    fun setState(state: StepState)
    fun getNextSteps(): List<Step>
    fun setNextSteps(steps: List<Step>)



    fun execute(): StepState

}