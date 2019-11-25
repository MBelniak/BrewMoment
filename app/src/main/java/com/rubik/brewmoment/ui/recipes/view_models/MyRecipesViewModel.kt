package com.rubik.brewmoment.ui.recipes.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rubik.brewmoment.data.Recipe
import com.rubik.brewmoment.data.RecipeRepository
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.RecipeAuthorTypeEnum
import com.rubik.brewmoment.model.step.WaitStep

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository(application)
    val recipes: LiveData<List<Recipe>> = getMyRecipes()

    private fun getMyRecipes(): LiveData<List<Recipe>> {
        val step1 = WaitStep("aaa", 1, 30)
        val recipe1: Recipe = Recipe(arrayOf(step1), "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 180, true)
        val data = MutableLiveData<List<Recipe>>()
        data.value = listOf(recipe1)
        return data
    }

}