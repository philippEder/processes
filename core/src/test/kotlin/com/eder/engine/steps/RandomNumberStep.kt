package com.eder.engine.steps

import com.eder.engine.addVariable
import com.eder.engine.model.Variable
import com.eder.engine.model.Process
import com.eder.engine.model.Step
import kotlin.random.Random

class RandomNumberStep(private val process: Process,
                       val randomNumberVariableName: String = "RANDOM_NUMBER"): Step(flow = process, key = "RandomInt") {



    override fun execute(): StepState {
        val random = Random.nextInt(0, 100)

        process.addVariable(Variable(randomNumberVariableName, random.toString()))

        return StepState.FINISHED
    }

}