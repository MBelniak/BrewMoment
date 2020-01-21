package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rubik.brewmoment.model.data.CommonRecipesData
import com.rubik.brewmoment.model.data.Recipe

class CommonRecipesViewModel(application: Application) : AndroidViewModel(application) {
    val recipes: List<Recipe> = getAllRecipes()

    private fun getAllRecipes(): List<Recipe> {
        return CommonRecipesData.getAll()

    }
}