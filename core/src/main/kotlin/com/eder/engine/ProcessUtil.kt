package com.eder.engine

import com.eder.engine.model.Process
import com.eder.engine.model.Step
import com.eder.engine.model.Step.StepState
import com.eder.engine.model.Variable


fun Process.visitEachStepList(op: (List<Step>) -> Unit) {
    val starStep = startStep ?: return
    val currentSteps = listOf(starStep)
    visitEachStepListFrom(currentSteps, op)
}

fun visitEachStepListFrom(currentSteps: List<Step>, op: (List<Step>) -> Unit) {
    op(currentSteps)
    currentSteps.forEach {
        if (it.nextSteps.isNotEmpty())
            visitEachStepListFrom(it.nextSteps, op)
    }
}

fun Process.visitEachStep(op: (Step) -> Unit) {
    startStep?.let { visitEachStepFrom(it, op) }

}

fun Process.visitEachStepFrom(currentStep: Step, op: (Step) -> Unit) {
    op(currentStep)
    currentStep.nextSteps.forEach {
        visitEachStepFrom(it, op)
    }

}

fun Process.isFinished(): Boolean {
    var isFinished = true

    visitEachStep {
        if (StepState.FINISHED != it.state)
            isFinished = false
    }

    return isFinished
}

fun Process.addVariable(variable: Variable) {
    context.variables[variable.key] = variable
}