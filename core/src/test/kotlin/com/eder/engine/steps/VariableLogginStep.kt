package com.eder.engine.steps

import com.eder.engine.model.Variable
import com.eder.engine.model.Process
import com.eder.engine.model.Step

class VariableLogginStep(private val process: Process,
                         private val variableName: String): Step(flow = process, key = "LogVar") {

    override fun execute(): StepState {
        val variable: Variable = process.context.variables[variableName] ?: throw IllegalArgumentException("variable was not set!")

        println("!!!!!!!!!!!!!!!!${variable.value}")

        return StepState.FINISHED
    }

}