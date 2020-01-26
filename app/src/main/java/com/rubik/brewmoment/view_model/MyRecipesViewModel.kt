package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipeRepository
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.WaitStep

class MyRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository()
    val recipes: LiveData<List<Recipe>> = getAllRecipes()

    private fun getAllRecipes(): LiveData<List<Recipe>> {
        val step1 = WaitStep("aaa", 3, 0)
        val recipe1 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val recipe2 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val recipe3 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val recipe4 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val recipe5 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val recipe6 = Recipe(
            "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 3, 0, GrindLevelEnum.MEDIUM, arrayOf(step1), 90, 0, true
        )
        val data = MutableLiveData<List<Recipe>>()
        data.value = listOf(recipe1, recipe2, recipe3, recipe4, recipe5, recipe6)
        return data
    }

}