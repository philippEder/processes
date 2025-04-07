package com.eder.engine.parser.model

import kotlinx.serialization.Serializable

@Serializable
class WorkflowStepJson(var id: String,
                       var key: String,
                       var state: String = "NEW")