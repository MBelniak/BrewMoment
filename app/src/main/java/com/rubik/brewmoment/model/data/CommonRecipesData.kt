package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

object CommonRecipesData {

    fun getAll(): List<Recipe>
    {
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), 90, 0
        ))
    }

    fun getByKey(key: String): Recipe
    {
        return Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, true, arrayOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), 90, 0
        )
    }

    fun getDefaultRecipe(): Recipe {
        return Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, true, arrayOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), 90, 0
        )
    }
}