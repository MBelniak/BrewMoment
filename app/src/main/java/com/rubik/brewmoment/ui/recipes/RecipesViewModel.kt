package com.rubik.brewmoment.ui.recipes

import android.app.Application
import androidx.lifecycle.*
import com.rubik.brewmoment.data.Recipe
import com.rubik.brewmoment.data.RecipeRepository
import com.rubik.brewmoment.model.RecipeAuthorTypeEnum

class RecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository(application)
    private val recipeAuthorType = arrayListOf<Int>()
    private val _index = MutableLiveData<Int>()
    init {
        _index.value = 1
    }
    val allRecipes: LiveData<List<Recipe>> = Transformations.map(_index) {
        when (_index.value) {
            RecipeAuthorTypeEnum.MY_RECIPES.authorType -> getMyRecipes()
            RecipeAuthorTypeEnum.BUILT_IN_RECIPES.authorType -> getBuiltInRecipes()
            RecipeAuthorTypeEnum.OTHER_RECIPES.authorType -> getOtherRecipes()
            else -> mutableListOf()
        }
    }

    private fun getOtherRecipes(): List<Recipe> {

    }

    private fun getBuiltInRecipes(): List<Recipe> {

    }

    private fun getMyRecipes(): List<Recipe> {

    }

    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}