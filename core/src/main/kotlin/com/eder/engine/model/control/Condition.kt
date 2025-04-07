package com.eder.engine.model.control

import com.eder.engine.model.Variable

class Condition(

    val leftValue: Variable,
    val rightValue: Variable,
    val conditionType: ConditionType

)