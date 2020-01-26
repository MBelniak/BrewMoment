package com.rubik.brewmoment.model.data

import android.content.Context
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

object RecipesDAO {


    fun getAll(): List<Recipe>
    {
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM,
            arrayOf(WaitStep("aaa", 1, 30)), 90, 0, true
        ))
    }

    fun getAll(eqType: EqTypeEnum): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getAllCommonRecipes(context: Context): List<Recipe>
    {
        return CommonRecipesData.getAll()
    }

    fun getAllMyRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getAllDripRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "bbbbbb", "aaaa", "aaaa", "desc",
            EqTypeEnum.DRIP, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getAllAeropressRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getAllChemexRecipes(): List<Recipe> {
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.CHEMEX, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getAllFrenchPressRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.FRENCH_PRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ),
            90, 0, true
        ))
    }

    fun getByKey(key: String): Recipe {
        return Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, arrayOf(WaitStep("aaa", 1, 30)), 90, 0, true
        )
    }

}