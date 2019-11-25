package com.rubik.brewmoment.ui.recipes.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rubik.brewmoment.data.Recipe
import com.rubik.brewmoment.data.RecipeRepository
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.step.Step
import com.rubik.brewmoment.model.step.WaitStep

class BuiltInRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository(application)
    val recipes: List<Recipe> = getBuiltInRecipes()

    private fun getBuiltInRecipes(): List<Recipe> {
        val step1 = WaitStep("aaa", 1, 30)
        val recipe1: Recipe = Recipe(arrayOf(step1), "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 180, true)
        return listOf(recipe1)

    }
}