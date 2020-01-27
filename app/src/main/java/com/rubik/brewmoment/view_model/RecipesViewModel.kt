package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.*
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class RecipesViewModel(application: Application) : AndroidViewModel(application) {
    var recipes: LiveData<List<Recipe>> = RecipesDAO.getAllRecipes()

    fun getMyFilteredRecipes(filt: Filtering, email: String?): List<Recipe> {
        if (filt == Filtering.ALL)
            return recipes.value?.filter { it.authorEmail == email } ?: listOf()
        return recipes.value?.filter { it.equipment.ordinal == filt.ordinal && it.authorEmail == email} ?: listOf()
    }

    fun getUsersFilteredRecipes(filt: Filtering): List<Recipe> {
        if (filt == Filtering.ALL)
            return recipes.value?.filter { it.isShared } ?: listOf()
        return recipes.value?.filter { it.equipment.ordinal == filt.ordinal && it.isShared} ?: listOf()
    }

    fun getRecipe(key: String): Recipe? {
        return RecipesDAO.getByKey(key)
    }
}