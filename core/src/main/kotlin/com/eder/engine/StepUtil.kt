package com.eder.engine

import com.eder.engine.model.Step

fun Step.connectTo(targetStep: Step) {
    nextSteps.add(targetStep)
    targetStep.previousStep = this
}