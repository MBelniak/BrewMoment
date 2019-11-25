package com.rubik.brewmoment.ui.recipes.view_models

import android.app.Application
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.rubik.brewmoment.data.Recipe
import com.rubik.brewmoment.data.RecipeRepository
import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.step.WaitStep

class OtherRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository(application)
    val recipes: List<Recipe> = getOtherRecipes()

    private fun getOtherRecipes(): List<Recipe> {
//        val recipes = repository.allRecipes
//        val currentUserEmail: FirebaseUser? = FirebaseAuth.getInstance().currentUser ?: return arrayListOf()
//        return recipes.filter { recipe -> recipe.authorEmail != currentUserEmail?.email }
        val step1 = WaitStep("aaa", 1, 30)
        val recipe1: Recipe = Recipe(arrayOf(step1), "aaa", "aaaa", "aaaa", "desc",
            EqTypeEnum.AEROPRESS, 180, true)
        return listOf(recipe1)
    }
}