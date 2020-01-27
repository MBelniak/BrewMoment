package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rubik.brewmoment.model.Filtering
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipesDAO

class MyRecipesViewModel(application: Application, val email: String) : AndroidViewModel(application) {
    var recipes: LiveData<List<Recipe>> = RecipesDAO.getAllRecipes()

    fun getFilteredRecipes(filt: Filtering): List<Recipe> {
        if (filt == Filtering.ALL)
            return recipes.value?.filter { recipe -> recipe.authorEmail == email} ?: listOf()
        return recipes.value?.filter { recipe -> recipe.equipment.ordinal == filt.ordinal
                    && recipe.authorEmail == email} ?: listOf()
    }
}