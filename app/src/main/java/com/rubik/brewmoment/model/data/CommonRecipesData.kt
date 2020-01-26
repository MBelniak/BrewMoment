package com.rubik.brewmoment.model.data

import android.content.Context
import com.beust.klaxon.Klaxon
import com.rubik.brewmoment.R
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

    val recipeList = arrayListOf(Recipe("aaa", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "0")
    , Recipe("bbb", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "1")
    , Recipe("ccc", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "2")
    , Recipe("ddd", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "3")
    , Recipe("eee", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "4")
    , Recipe("fff", "aaaa", "aaaa", "desc",
        EqTypeEnum.AEROPRESS, 3, 0,
        GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30),
            WaitStep("aaa", 1, 30)), 90, 0, true, "5"))

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
            GrindLevelEnum.MEDIUM, arrayOf(WaitStep("", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), 90, 0, true
        )
    }
}