package com.eder.engine

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement.ProcessElementState.*
import com.eder.engine.model.Variable
import com.eder.engine.model.control.Condition
import com.eder.engine.model.control.ConditionType
import com.eder.engine.model.control.IfElse
import com.eder.engine.steps.LoggingStep
import com.eder.engine.steps.RandomNumberStep
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IfElseTest {

    val engine = Engine()

    @Test
    fun `if else basic - true result`() {

        val process = Process()

        val startStep = LoggingStep(process, "step1")
        val trueStep = LoggingStep(process, "step2")
        val falseStep = RandomNumberStep(process)

        val ifElse = IfElse(trueElement = trueStep,
                            falseElement = falseStep,
                            condition = getEqualsTrueCondition())

        startStep.connectTo(ifElse)

        process.startStep = startStep
        engine.proceed(process)

        assertEquals(FINISHED, trueStep.state)
        assertEquals(NEW, falseStep.state)
    }


    @Test
    fun `if else basic - false result`() {

        val process = Process()

        val startStep = LoggingStep(process, "step1")
        val trueStep = LoggingStep(process, "step2")
        val falseStep = RandomNumberStep(process)

        val ifElse = IfElse(trueElement = trueStep,
            falseElement = falseStep,
            condition = getEqualsFalseCondition())

        startStep.connectTo(ifElse)

        process.startStep = startStep
        engine.proceed(process)

        assertEquals(NEW, trueStep.state)
        assertEquals(FINISHED, falseStep.state)
    }


    fun getEqualsTrueCondition(): Condition {
        val variable1 =  Variable("key1", "value")
        val variable2 =  Variable("key2", "value")
        val conditionType = ConditionType.EQUALS

        return Condition(variable1, variable2, conditionType)
    }

    fun getEqualsFalseCondition(): Condition {
        val variable1 =  Variable("key1", "value")
        val variable2 =  Variable("key2", "otherValue")
        val conditionType = ConditionType.EQUALS

        return Condition(variable1, variable2, conditionType)
    }

}