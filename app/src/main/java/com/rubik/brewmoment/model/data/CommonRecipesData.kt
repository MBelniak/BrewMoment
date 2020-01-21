package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

object CommonRecipesData {

//    private var recipesList: List<Recipe> = arrayListOf(
//        Recipe(arrayOf(WaitStep("aaa", 1, 30)), "aaa", "aaaa", "aaaa", "desc",
//        EqTypeEnum.AEROPRESS, 180, GrindLevelEnum.MEDIUM, true),
//        )

//    val step1 =
//    val recipe1: Recipe =


    fun getAll(): List<Recipe>
    {
        return arrayListOf(Recipe("aaa", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(WaitStep("aaa", 1, 30)), 90))
    }

    fun getById(id: Int): Recipe
    {
        return Recipe("aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, true, arrayOf(WaitStep("aaa", 1, 30)), 90)
    }
}