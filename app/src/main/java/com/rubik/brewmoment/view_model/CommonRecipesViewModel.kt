package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class CommonRecipesViewModel(application: Application) : AndroidViewModel(application) {
    val recipes: List<Recipe> = getAllRecipes()

    private fun getAllRecipes(): List<Recipe> {
        return CommonRecipesData.getAll()
    }

    fun getFilteredRecipes(filt: Filtering): List<Recipe> {
        if (filt == Filtering.ALL)
            return recipes
        return recipes.filter { recipe ->  recipe.equipment.ordinal == filt.ordinal}
    }
}