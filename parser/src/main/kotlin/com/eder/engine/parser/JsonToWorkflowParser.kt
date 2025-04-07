package com.eder.engine.parser

import com.eder.engine.model.Process
import com.eder.engine.model.ProcessElement.ProcessElementState
import com.eder.engine.model.Step
import com.eder.engine.parser.model.WorkflowJson
import kotlinx.serialization.json.Json

class JsonToWorkflowParser {

    fun parse(json: String): Process {
        val decoder = Json { ignoreUnknownKeys = true}
        val workflow = decoder.decodeFromString<WorkflowJson>(json)

        val process = Process(id = workflow.id)

        val steps = workflow.steps.map {
            Step(id = it.id,
                 flow = process,
                 state = ProcessElementState.valueOf(it.state),
                 key = it.key
            )
        }

        steps.forEach {
            val sourceSteps = searchForSourceSteps(workflow, steps, it.id)
            val targetSteps = searchForTargetSteps(workflow, steps, it.id)

            //TODO: more than one source step possible?
            it.previous = sourceSteps.firstOrNull()
            it.next = targetSteps.toMutableList()

            if (it.previous == null) {
                process.startStep = it
            }
        }

        return process
    }

    fun searchForSourceSteps(workflow: WorkflowJson, allSteps: List<Step>, stepId: String): List<Step> {
        val sourceStepIds = workflow.links.filter { it.target == stepId }.map { it.source }
        val sourceSteps = allSteps.filter { sourceStepIds.contains(it.id) }
        return sourceSteps
    }

    fun searchForTargetSteps(workflow: WorkflowJson, allSteps: List<Step>, stepId: String): List<Step> {
        val targetStepIds = workflow.links.filter { it.source == stepId }.map { it.target }
        val targetSteps = allSteps.filter { targetStepIds.contains(it.id) }
        return targetSteps
    }

}