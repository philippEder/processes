package com.eder.engine.parser

import kotlinx.serialization.json.Json
import com.eder.engine.model.Process
import com.eder.engine.model.Step

class JsonToWorkflowParser {

    fun parse(json: String): Process? {
        val workflow = Json.Default.decodeFromString<WorkflowJson>(json)

        val process = Process()

        workflow.steps.forEach { step ->
            val sourceSteps = getSourceStepsFor(workflow, step)
            val targetSteps = getTargetStepsFor(workflow, step)

           // val stepImpl = Step(process)




        }



        return Process()
    }

    fun getSourceStepsFor(workflow: WorkflowJson, step: WorkflowStepJson): List<WorkflowStepJson> {
        val sourceStepIds = workflow.links.filter { it.target == step.id }.map { it.source }
        val sourceSteps = workflow.steps.filter { sourceStepIds.contains(it.id) }
        return sourceSteps
    }

    fun getTargetStepsFor(workflow: WorkflowJson, step: WorkflowStepJson): List<WorkflowStepJson> {
        val targetStepIds = workflow.links.filter { it.source == step.id }.map { it.target }
        val targetSteps = workflow.steps.filter { targetStepIds.contains(it.id) }
        return targetSteps
    }

}