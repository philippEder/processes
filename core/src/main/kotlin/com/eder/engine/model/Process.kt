package com.eder.engine.model

import java.util.UUID

class Process(

    var id: String = UUID.randomUUID().toString(),
    var startStep: Step? = null,
    val context: ProcessContext = ProcessContext(),
    var currentSteps: List<Step> = listOf()

)