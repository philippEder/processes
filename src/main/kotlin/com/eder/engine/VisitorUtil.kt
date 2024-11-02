package com.eder.engine


fun Process.visitEachStepList(op: (List<Step>) -> Unit) {
    val starStep = startStep ?: return
    val currentSteps = listOf(starStep)
    visitEachStepListFrom(currentSteps, op)
}

fun visitEachStepListFrom(currentSteps: List<Step>, op: (List<Step>) -> Unit) {
    op(currentSteps)
    currentSteps.forEach {
        if (it.getNextSteps().isNotEmpty())
            visitEachStepListFrom(it.getNextSteps(), op)
    }
}

fun Process.visitEachStep(op: (Step) -> Unit) {
    startStep?.let { visitEachStepFrom(it, op) }

}

fun Process.visitEachStepFrom(currentStep: Step, op: (Step) -> Unit) {
    op(currentStep)
    currentStep.getNextSteps().forEach {
        visitEachStepFrom(it, op)
    }

}

fun Process.isFinished(): Boolean {
    var isFinished = true

    visitEachStep {
        if (StepState.FINISHED != it.getState())
            isFinished = false
    }

    return isFinished
}