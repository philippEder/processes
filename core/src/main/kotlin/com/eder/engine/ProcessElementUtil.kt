package com.eder.engine

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement
import com.eder.engine.model.Variable


fun Process.visitEachElementList(op: (List<ProcessElement>) -> Unit) {
    val starStep = startStep ?: return
    val currentElements = listOf(starStep)
    visitEachElementListFrom(currentElements, op)
}

fun visitEachElementListFrom(currentElements: List<ProcessElement>, op: (List<ProcessElement>) -> Unit) {
    op(currentElements)
    currentElements.forEach {
        if (it.next.isNotEmpty())
            visitEachElementListFrom(it.next, op)
    }
}

fun Process.visitEachElement(op: (ProcessElement) -> Unit) {
    startStep?.let { visitEachElementFrom(it, op) }

}

fun Process.visitEachElementFrom(currentElement: ProcessElement, op: (ProcessElement) -> Unit) {
    op(currentElement)
    currentElement.next.forEach {
        visitEachElementFrom(it, op)
    }

}

fun Process.isFinished(): Boolean {
    var isFinished = true

    visitEachElement {
        if (ProcessElement.ProcessElementState.FINISHED != it.state)
            isFinished = false
    }

    return isFinished
}

fun Process.addVariable(variable: Variable) {
    context.variables[variable.key] = variable
}