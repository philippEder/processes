package com.eder.engine.steps

import com.eder.engine.model.Variable
import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement
import com.eder.engine.model.Step

class VariableLogginStep(private val process: Process,
                         private val variableName: String): Step(flow = process, key = "LogVar") {

    override fun execute(): ProcessElement.ProcessElementState {
        val variable: Variable = process.context.variables[variableName] ?: throw IllegalArgumentException("variable was not set!")

        println("!!!!!!!!!!!!!!!!${variable.value}")

        return ProcessElement.ProcessElementState.FINISHED
    }

}