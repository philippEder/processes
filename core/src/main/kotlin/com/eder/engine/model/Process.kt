package com.eder.engine.model

class Process(var startStep: Step? = null,
              val context: ProcessContext = ProcessContext(),
              var currentSteps: List<Step> = listOf())