package com.rubik.brewmoment.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.step.Step

class Recipe(val steps: Array<Step>, val name: String, val author: String, val authorEmail: String,
             val description: String, val equipment: EqTypeEnum, val brewTime: Int, val isDefault: Boolean)
{
    fun brewTimeToString(): String {
        return "${brewTime/60} : ${brewTime%60}"
    }

    var key: String = ""
}