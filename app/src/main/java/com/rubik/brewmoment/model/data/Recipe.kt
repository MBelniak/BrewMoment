package com.rubik.brewmoment.model.data

import com.google.firebase.database.Exclude
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.Step
import java.lang.StringBuilder

class Recipe(
    var title: String = "",
    var author: String = "",
    var authorEmail: String = "",
    var description: String = "",
    var equipment: EqTypeEnum = EqTypeEnum.AEROPRESS,
    var brewTimeMinutes: Int = 0,
    var brewTimeSeconds: Int = 0,
    var grindLevel: GrindLevelEnum = GrindLevelEnum.MEDIUM,
    var temperature: Float = 0f,
    var dose: Float = 0f,
    var totalWater: Float = 0f,
    var steps: List<Step> = listOf(),
    var isShared: Boolean = true,
    var isDefault: Boolean = false,
    var key: String = "0"
)
{
    @Exclude
    fun brewTimeToString(): String {
        if (brewTimeSeconds < 10)
            return "${brewTimeMinutes}:0${brewTimeSeconds}"
        return "${brewTimeMinutes}:${brewTimeSeconds}"
    }

    @Exclude
    fun getStepsAsString(): String {
        val result = StringBuilder()
        for (step in steps)
            result.append("\n" + step.description)
        return result.toString()
    }

}