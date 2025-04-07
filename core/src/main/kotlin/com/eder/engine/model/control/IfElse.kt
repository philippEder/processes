package com.eder.engine.model.control

import com.eder.engine.model.ProcessElement
import java.util.UUID

class IfElse(

    val id: String = UUID.randomUUID().toString(),
    val trueElement: ProcessElement,
    val falseElement: ProcessElement,
    val condition: Condition,

    override var previous: ProcessElement? = null,
    override var state: ProcessElement.ProcessElementState = ProcessElement.ProcessElementState.NEW,
    override var next: MutableList<ProcessElement> = mutableListOf()


) : ProcessElement {


    override fun execute(): ProcessElement.ProcessElementState {
        next = if (condition.evaluate())
                    mutableListOf<ProcessElement>(trueElement)
               else
                    mutableListOf<ProcessElement>(falseElement)

        return ProcessElement.ProcessElementState.FINISHED
    }

}

