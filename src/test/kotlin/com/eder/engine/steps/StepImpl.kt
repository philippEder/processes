package com.eder.engine.steps

import com.eder.engine.Step
import com.eder.engine.StepState
import com.eder.engine.Process

abstract class StepImpl(private val flow: Process): Step {

    private var state = StepState.NEW
    private var nextSteps = listOf<Step>()
    private var previousStep: Step? = null

    override fun getWorkflow()= flow
    override fun getState() = state
    override fun setState(state: StepState) {
        this.state = state
    }

    override fun getPreviousStep(): Step? {
        return previousStep
    }

    override fun setPreviousStep(step: Step) {
        previousStep = step
    }

    override fun setNextSteps(steps: List<Step>) {
        nextSteps = steps
        steps.forEach {
            it.setPreviousStep(this)
        }
    }
    override fun getNextSteps() = nextSteps

}