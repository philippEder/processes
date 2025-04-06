package com.eder.engine

import com.eder.engine.steps.LoggingStep
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertTrue
import com.eder.engine.model.Process
import com.eder.engine.model.Step.StepState.*
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

        step1.connectTo(step2)

        process.startStep = step1

        engine.proceed(process)

        assertEquals(step2, process.currentSteps[0])
    }

    @Test
    fun `visit all steps`() {
        val process = Process()

        val step1 = LoggingStep(process,"step1")
        val step2 = LoggingStep(process,"step2")

        step1.connectTo(step2)

        process.startStep = step1

        process.visitEachStep { it.state = IN_PROGRESS }

        assertEquals(step1.state, IN_PROGRESS)
        assertEquals(step2.state, IN_PROGRESS)
    }

    @Test
    fun `all steps are finished after execution`() {
        val process = Process()

        val step1 = LoggingStep(process,"step1")
        val step2 = LoggingStep(process,"step2")

        step1.connectTo(step2)

        process.startStep = step1

        engine.proceed(process)

        process.visitEachStep { assertEquals(FINISHED, it.state) }
    }

    @Test
    fun `first step creates variable second step uses it`() {
        val process = Process()

        val step1 = RandomNumberStep(process,"RANDOM_NUMBER_VAR")
        val step2 = VariableLogginStep(process,"RANDOM_NUMBER_VAR")

        step1.connectTo(step2)

        process.startStep = step1

        engine.proceed(process)
    }

    @Test
    fun `workflow does not continue with previous step not finished`() {
        val process = Process()

        val step1 = LoggingStep(process,"step 1")
        val step2 = OkStep(process)
        val step3 = LoggingStep(process,"step 3")

        step1.connectTo(step2)
        step2.connectTo(step3)

        process.startStep = step1

        engine.proceed(process)
        assertEquals(FINISHED, step1.state)
        assertEquals(WAITING, step2.state)
        assertEquals(NEW, step3.state)

        //WHEN
        step2.ok()
        engine.proceed(process)


        //THEN
        assertEquals(FINISHED, step1.state)
        assertEquals(FINISHED, step2.state)
        assertEquals(FINISHED, step3.state)

    }


    /*****************************************
                      step1.1 -> step 1.2
        startStep ->
                      step2.1 -> step.2.2
     *****************************************/

    @Test
    fun `executed paralell steps`() {
        val process = Process()

        val startStep = LoggingStep(process,"step 0")
        val step11 = LoggingStep(process,"step 1.1")
        val step12 = LoggingStep(process,"step 1.2")
        val step21 = LoggingStep(process,"step 2.1")
        val step22 = LoggingStep(process,"step 2.2")


        startStep.connectTo(step11)
        startStep.connectTo(step21)
        step11.connectTo(step12)
        step21.connectTo(step22)
        process.startStep = startStep

        //WHEN
        engine.proceed(process)

        //THEN
        assertTrue(process.isFinished())
    }

}