package com.eder.engine.steps

import com.eder.engine.StepState
import com.eder.engine.Process

class OkStep(private val process: Process): StepImpl(process) {

    private var okClicked = false

    override fun execute() =
        if (okClicked)
            StepState.FINISHED
        else
            StepState.WAITING

    fun ok() {
        okClicked = true
    }


}