package com.rubik.brewmoment.view_model

import android.app.Application
import androidx.lifecycle.*
import com.rubik.brewmoment.model.data.Recipe
import com.rubik.brewmoment.model.data.RecipeRepository
import com.rubik.brewmoment.model.data.RecipesDAO

class UsersRecipesViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: RecipeRepository = RecipeRepository(application)
    val recipes: LiveData<List<Recipe>> = getAllRecipes()

    private fun getAllRecipes(): LiveData<List<Recipe>> {
//        val recipes = repository.allRecipes
//        val currentUserEmail: FirebaseUser? = FirebaseAuth.getInstance().currentUser ?: return arrayListOf()
//        return recipes.filter { recipe -> recipe.authorEmail != currentUserEmail?.email }
        val data = MutableLiveData<List<Recipe>>()
        data.value = RecipesDAO.getAll()
        return data
    }
}