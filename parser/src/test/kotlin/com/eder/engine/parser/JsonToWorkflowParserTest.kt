package com.eder.engine.parser

import org.junit.jupiter.api.Test
import com.eder.engine.model.Process
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

class JsonToWorkflowParserTest {

    @Test
    fun `parse workflow to json`() {
        //GIVEN
        val json = this.javaClass.getResource("/simpleWorkflow.json")?.readText() ?: throw IllegalArgumentException("Testfile not found")

        //WHEN
        val process: Process = JsonToWorkflowParser().parse(json)

        //THEN
        assertNotNull(process)
    }


    @Test
    fun `parse workflow to json - assert some values `() {
        //GIVEN
        val json = this.javaClass.getResource("/simpleWorkflow.json")?.readText() ?: throw IllegalArgumentException("Testfile not found")

        //WHEN
        val process: Process = JsonToWorkflowParser().parse(json)

        //THEN
        assertEquals("12345", process.id)
        assertNotNull(process.startStep)

    }

}