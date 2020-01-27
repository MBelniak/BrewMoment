package com.rubik.brewmoment.model.data

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

object RecipesDAO {

    private val databaseRef = BrewMomentDatabase.getReference("recipes")
    val allRecipes: MutableLiveData<List<Recipe>> = MutableLiveData()

    init {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    allRecipes.postValue(dataSnapshot.children.map { it.getValue(Recipe::class.java) } as List<Recipe>?)
                }
            }
        })
    }

    fun insert(recipe: Recipe) {
        InsertRecipeAsyncTask(databaseRef).execute(recipe)
    }

    fun update(recipe: Recipe) {
        UpdateRecipeAsyncTask(databaseRef).execute(recipe)
    }


    fun delete(recipe: Recipe) {
        DeleteRecipeAsyncTask(databaseRef).execute(recipe)
    }

    private class InsertRecipeAsyncTask(val databaseRef: DatabaseReference) :
            AsyncTask<Recipe, Unit, Unit>() {
        override fun doInBackground(vararg recipe: Recipe) {
            val key: String? = databaseRef.push().key
            if (key != null) {
                recipe[0].key = key
            }
           key?.let { databaseRef.child(it).setValue(recipe[0]) }
        }
    }

    private class UpdateRecipeAsyncTask(val databaseRef: DatabaseReference) :
        AsyncTask<Recipe, Unit, Unit>() {
        override fun doInBackground(vararg recipe: Recipe) {
            val key = recipe[0].key
            databaseRef.child(key).setValue(recipe[0])
        }
    }

    private class DeleteRecipeAsyncTask(val databaseRef: DatabaseReference) :
        AsyncTask<Recipe, Unit, Unit>() {
        override fun doInBackground(vararg recipe: Recipe) {
            val key = recipe[0].key
            databaseRef.child(key).removeValue()
        }
    }

    fun getAllRecipes(): LiveData<List<Recipe>>
    {
        return allRecipes
    }


    fun getByKey(key: String): Recipe? {
        if (allRecipes.value != null)
            for (recipe in allRecipes.value!!)
                if (recipe.key == key)
                    return recipe
        return null
    }


}