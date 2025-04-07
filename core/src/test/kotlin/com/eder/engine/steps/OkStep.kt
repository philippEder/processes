package com.eder.engine.steps

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement
import com.eder.engine.model.Step

class OkStep(private val process: Process): Step(flow = process, key = "OK") {

    private var okClicked = false

    override fun execute() =
        if (okClicked)
            ProcessElement.ProcessElementState.FINISHED
        else
            ProcessElement.ProcessElementState.WAITING

    fun ok() {
        okClicked = true
    }



}