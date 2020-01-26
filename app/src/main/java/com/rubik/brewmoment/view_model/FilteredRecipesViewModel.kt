package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class FilteredRecipesViewModel(application: Application) : AndroidViewModel(application) {

    var recipes: List<Recipe> = RecipesDAO.getAll()
    var filtering: Filtering = Filtering.All
        set(value) {
            if (field != value)
                recipes = getFilteredRecipes(value)
            field = value
        }

    private fun getFilteredRecipes(filt: Filtering): List<Recipe> {
        return when (filt) {
            Filtering.All -> RecipesDAO.getAll()
            Filtering.Aeropress -> RecipesDAO.getAllAeropressRecipes()
            Filtering.Drip -> RecipesDAO.getAllDripRecipes()
            Filtering.Chemex -> RecipesDAO.getAllChemexRecipes()
            Filtering.FrenchPress -> RecipesDAO.getAllFrenchPressRecipes()
        }
    }
}

enum class Filtering {
    All, Drip, Aeropress, FrenchPress, Chemex
}