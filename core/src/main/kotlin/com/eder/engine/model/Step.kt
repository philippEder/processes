package com.eder.engine.model

import java.util.UUID

//TODO: maybe better access modifiers
open class Step(var id: String = UUID.randomUUID().toString(),
                val flow: Process,
                var state: StepState = StepState.NEW,
                var nextSteps: MutableList<Step> = mutableListOf(),
                var previousStep: Step? = null ,
                val key: String) {

    enum class StepState {
        NEW,
        IN_PROGRESS,
        WAITING,
        FAILED,
        FINISHED
    }

    //transient
    open fun execute(): StepState {
        throw IllegalArgumentException("cant call this on Step super class")
    }

}