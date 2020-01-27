package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.*
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class UsersRecipesViewModel(application: Application) : AndroidViewModel(application) {
    var recipes: LiveData<List<Recipe>> = RecipesDAO.getAllUsersRecipes()

    fun getFilteredRecipes(filt: Filtering): List<Recipe> {
        if (filt == Filtering.ALL)
            return recipes.value ?: listOf()
        return recipes.value?.filter { recipe ->  recipe.equipment.ordinal == filt.ordinal} ?: listOf()
    }
}