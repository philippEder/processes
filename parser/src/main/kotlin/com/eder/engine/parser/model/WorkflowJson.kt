package com.eder.engine.parser.model

import kotlinx.serialization.Serializable


@Serializable
data class WorkflowJson(

    val id: String,
    val steps: List<WorkflowStepJson> = listOf(),
    val links: List<WorkflowStepLinkJson> = listOf()

)