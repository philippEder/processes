package com.eder.engine.steps

import com.eder.engine.model.Process
import com.eder.engine.model.Step

class OkStep(private val process: Process): Step(flow = process, key = "OK") {

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