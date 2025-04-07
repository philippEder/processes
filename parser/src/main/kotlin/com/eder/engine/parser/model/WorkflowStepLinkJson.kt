package com.eder.engine.parser.model

import kotlinx.serialization.Serializable

@Serializable
class WorkflowStepLinkJson(val source: String, val target: String)