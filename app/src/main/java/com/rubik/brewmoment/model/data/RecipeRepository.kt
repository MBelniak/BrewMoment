package com.rubik.brewmoment.model.data

import android.app.Application
import android.os.AsyncTask
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class RecipeRepository(application: Application) {

    val databaseRef = BrewMomentDatabase.getReference("recipes")
    val allRecipes: MutableList<Recipe> = mutableListOf()

    init {
        databaseRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    allRecipes.clear()
                    for (rec in p0.children) {
                        val recipe = rec.getValue(Recipe::class.java)
                        allRecipes.add(recipe!!)
                    }
                }
            }

        })
    }

    fun insert(recipe: Recipe) {
        val insertRecipeAsyncTask = InsertRecipeAsyncTask(databaseRef).execute(recipe)
    }

    fun update(recipe: Recipe) {
        val updateRecipeAsyncTask = UpdateRecipeAsyncTask(databaseRef).execute(recipe)
    }


    fun delete(recipe: Recipe) {
        val deleteRecipeAsyncTask = DeleteRecipeAsyncTask(databaseRef).execute(recipe)
    }


    companion object {
        private class InsertRecipeAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<Recipe, Unit, Unit>() {

            override fun doInBackground(vararg recipe: Recipe) {
                val key: String? = databaseRef.push().key
                if (key != null) {
                    recipe[0].key = key
                }
                key?.let { databaseRef.child(it).setValue(recipe)}
            }
        }

        private class UpdateRecipeAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<Recipe, Unit, Unit>() {
            override fun doInBackground(vararg recipe: Recipe) {
                val key = recipe[0].key
                databaseRef.child(key).setValue(recipe)
            }
        }

        private class DeleteRecipeAsyncTask(val databaseRef: DatabaseReference) : AsyncTask<Recipe, Unit, Unit>() {
            override fun doInBackground(vararg recipe: Recipe) {
                val key = recipe[0].key
                databaseRef.child(key).removeValue()
            }
        }
    }
}