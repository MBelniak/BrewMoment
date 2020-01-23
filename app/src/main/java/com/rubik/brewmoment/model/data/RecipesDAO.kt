package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

object RecipesDAO {


    fun getAll(): List<Recipe>
    {
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM,
            true, arrayOf(WaitStep("aaa", 1, 30)), 90, 0
        ))
    }

    fun getAll(eqType: EqTypeEnum): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ), 90, 0
        ))
    }

    fun getAllCommonRecipes(): List<Recipe>
    {
        return CommonRecipesData.getAll()
    }

    fun getAllMyRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ), 90, 0
        ))
    }

    fun getAllDripRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "bbbbbb", "aaaa", "aaaa", "desc",
            EqTypeEnum.DRIP, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ), 90, 0
        ))
    }

    fun getAllAeropressRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ), 90, 0
        ))
    }

    fun getAllFrenchPressRecipes(): List<Recipe>
    {
        //TODO
        return arrayListOf(Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.FRENCH_PRESS, 3, 0, GrindLevelEnum.MEDIUM, true,
            arrayOf(
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)
            ), 90, 0
        ))
    }

    fun getByKey(key: String): Recipe {
        return Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, true, arrayOf(WaitStep("aaa", 1, 30)), 90, 0
        )
    }

    fun saveResult(
        coffee: String,
        notes: String,
        saveAsFavourites: Boolean,
        recipeKey: String,
        default: Boolean
    ) {

    }
}