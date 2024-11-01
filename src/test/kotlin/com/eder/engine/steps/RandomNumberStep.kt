package com.eder.engine.steps

import com.eder.engine.StepState
import com.eder.engine.Variable
import com.eder.engine.Process
import kotlin.random.Random

class RandomNumberStep(private val process: Process,
                       val randomNumberVariableName: String = "RANDOM_NUMBER"): StepImpl(process) {



    override fun execute(): StepState {
        val random = Random.nextInt(0, 100)

        process.addVariable(Variable(randomNumberVariableName, random.toString()))

        return StepState.FINISHED
    }

}