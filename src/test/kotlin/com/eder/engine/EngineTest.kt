package com.eder.engine

import com.eder.engine.steps.LoggingStep
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import com.eder.engine.StepState.*
import com.eder.engine.steps.OkStep
import com.eder.engine.steps.RandomNumberStep
import com.eder.engine.steps.VariableLogginStep

class EngineTest {

    val engine = Engine()

    @Test
    fun `current steps is last step after workflow execution`() {
        val process = Process()

        val step1 = LoggingStep(process, "step1")
        val step2 = LoggingStep(process, "step2")
        step1.setNextSteps(listOf(step2))

        process.startStep = step1

        engine.execute(process)

        assertEquals(step2, process.currentSteps[0])
    }

    @Test
    fun `visit all steps`() {
        val process = Process()

        val step1 = LoggingStep(process,"step1")
        val step2 = LoggingStep(process,"step2")
        step1.setNextSteps(listOf(step2))

        process.startStep = step1

        visitEachStep(process) { it.setState(IN_PROGRESS) }

        assertEquals(step1.getState(), IN_PROGRESS)
        assertEquals(step2.getState(), IN_PROGRESS)
    }

    @Test
    fun `all steps are finished after execution`() {
        val process = Process()

        val step1 = LoggingStep(process,"step1")
        val step2 = LoggingStep(process,"step2")
        step1.setNextSteps(listOf(step2))

        process.startStep = step1

        engine.execute(process)

        visitEachStep(process) { assertEquals(FINISHED, it.getState()) }
    }

    @Test
    fun `first step creates variable second step uses it`() {
        val process = Process()

        val step1 = RandomNumberStep(process,"RANDOM_NUMBER_VAR")
        val step2 = VariableLogginStep(process,"RANDOM_NUMBER_VAR")

        step1.setNextSteps(listOf(step2))

        process.startStep = step1

        engine.execute(process)
    }

    @Test
    fun `workflow does not continue with previous step not finished`() {
        val process = Process()

        val step1 = LoggingStep(process,"step 1")
        val step2 = OkStep(process)
        val step3 = LoggingStep(process,"step 3")

        step1.setNextSteps(listOf(step2))
        step2.setNextSteps(listOf(step3))

        process.startStep = step1

        engine.execute(process)
        assertEquals(FINISHED, step1.getState())
        assertEquals(WAITING, step2.getState())
        assertEquals(NEW, step3.getState())

        //WHEN
        step2.ok()
        engine.execute(process)


        //THEN
        assertEquals(FINISHED, step1.getState())
        assertEquals(FINISHED, step2.getState())
        assertEquals(FINISHED, step3.getState())

    }

}