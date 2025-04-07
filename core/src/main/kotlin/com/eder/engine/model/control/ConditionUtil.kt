package com.eder.engine.model.control


//TODO: inheritance instead of switch??
fun Condition.evaluate() = when (conditionType) {
        ConditionType.EQUALS -> leftValue.value == rightValue.value
        ConditionType.EUQALS_IGNORE_CASE -> leftValue.value?.lowercase()?.equals(rightValue.value?.lowercase()) == true
        ConditionType.CONTAINS -> false
}
