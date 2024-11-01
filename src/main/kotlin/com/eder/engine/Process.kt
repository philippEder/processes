package com.eder.engine

class Process(var startStep: Step? = null,
              val context: ProcessContext = ProcessContext(),
              var currentSteps: List<Step> = listOf()) {

    fun addVariable(variable: Variable) {
        context.variables[variable.key] = variable
    }

}