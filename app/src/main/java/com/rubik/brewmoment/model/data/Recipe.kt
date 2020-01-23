package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.Step

class Recipe(
    val title: String,
    val author: String,
    val authorEmail: String,
    val description: String,
    val equipment: EqTypeEnum,
    val brewTimeMinutes: Int,
    val brewTimeSeconds: Int,
    val grindLevel: GrindLevelEnum,
    val isDefault: Boolean,
    val steps: Array<Step>,
    val temperature: Int,
    val doze: Int
)
{
    fun getTemperature(): String {
        return "${temperature}°C"
    }

    fun brewTimeToString(): String {
        if (brewTimeSeconds < 10)
            return "${brewTimeMinutes}:0${brewTimeSeconds}"
        return "${brewTimeMinutes}:${brewTimeSeconds}"
    }

    fun getStepsAsString(): String {
        return "aaaaa" //TODO
    }

    var key: String = ""
}