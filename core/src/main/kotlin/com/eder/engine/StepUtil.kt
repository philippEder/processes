package com.eder.engine

import com.eder.engine.model.ProcessElement

fun ProcessElement.connectTo(targetStep: ProcessElement) {
    next.add(targetStep)
    targetStep.previous = this
}