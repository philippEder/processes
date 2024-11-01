package com.eder.engine.steps

import com.eder.engine.StepState
import com.eder.engine.Variable
import com.eder.engine.Process

class VariableLogginStep(private val process: Process,
                         private val variableName: String): StepImpl(process) {

    override fun execute(): StepState {
        val variable: Variable = process.context.variables[variableName] ?: throw IllegalArgumentException("variable was not set!")

        println("!!!!!!!!!!!!!!!!${variable.value}")

        return StepState.FINISHED
    }

}