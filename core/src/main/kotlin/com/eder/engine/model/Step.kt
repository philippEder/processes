package com.eder.engine.model

import java.util.UUID

//TODO: maybe better access modifiers
open class Step(var id: String = UUID.randomUUID().toString(),
                val flow: Process,
                val key: String,

                override var state: ProcessElement.ProcessElementState = ProcessElement.ProcessElementState.NEW,
                override var next: MutableList<ProcessElement> = mutableListOf(),
                override var previous: ProcessElement? = null): ProcessElement {



    //transient
    override fun execute(): ProcessElement.ProcessElementState {
        throw IllegalArgumentException("cant call this on Step super class")
    }

}