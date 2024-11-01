package com.eder.engine


fun visitEachStepList(process: Process, op: (List<Step>) -> Unit) {
    val starStep = process.startStep ?: return
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

fun visitEachStep(process: Process, op: (Step) -> Unit) {
    process.startStep?.let { visitEachStepFrom(it, op) }

}

fun visitEachStepFrom(currentStep: Step, op: (Step) -> Unit) {
    op(currentStep)
    currentStep.getNextSteps().forEach {
        visitEachStepFrom(it, op)
    }

}