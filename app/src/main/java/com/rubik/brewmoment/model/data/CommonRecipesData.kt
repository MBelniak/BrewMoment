package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

object CommonRecipesData {

//    fun getAll(context: Context): List<Recipe>
//    {
//        val arr = arrayListOf<Recipe>()
//        val inputStream = context.resources.openRawResource(R.raw.common_recipes)
//        val result: List<Recipe>? = Klaxon().parseArray(inputStream)
//        if (result != null) {
//            for(obj in result) {
//                arr.add(obj)
//            }
//        }
//        return arr
//    }

    private val recipeList = arrayListOf(Recipe(
        "aaa", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), false, isDefault = true, key = "0"
    )
    , Recipe(
            "bbb", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), false, isDefault = true, key = "1"
        )
    , Recipe(
            "ccc", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), false, isDefault = true, key = "2"
        )
    , Recipe(
            "ddd", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), false, isDefault = true, key = "3"
        )
    , Recipe(
            "eee", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), false, isDefault = true, key = "4"
        )
    , Recipe(
            "fff", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), isShared = false, isDefault = true, key = "5"
        ))

    fun getAll(): List<Recipe>
    {
        return recipeList
    }

    fun getByKey(key: String): Recipe
    {
        if (key.toInt() < recipeList.size)
            return recipeList[key.toInt()]
        return getDefaultRecipe()
    }

    fun getDefaultRecipe(): Recipe {
        return Recipe(
            "Sample Recipe", "Noone", "", "Sample description",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, listOf(WaitStep("", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), isShared = false, isDefault = true
        )
    }
}