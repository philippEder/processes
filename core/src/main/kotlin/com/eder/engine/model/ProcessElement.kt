package com.eder.engine.model

interface ProcessElement {

    enum class ProcessElementState {
        NEW,
        IN_PROGRESS,
        WAITING,
        FAILED,
        FINISHED
    }

    var next: MutableList<ProcessElement>
    var previous: ProcessElement?
    var state: ProcessElementState

    fun execute(): ProcessElementState
}