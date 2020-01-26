package com.rubik.brewmoment.model.data

import android.os.SystemClock
import java.sql.Date

object BrewResultsDAO {
    fun getById(key: String, isMine: Boolean): BrewResult {
        return BrewResult(2, 50, "Kostaryka La Candelilla",
            "Acidic", true, Date(SystemClock.currentThreadTimeMillis()),
            isRecipeDefault = true,
            isResultShared = false
        )
    }

    fun saveResult(
        coffee: String,
        notes: String,
        saveAsFavourites: Boolean,
        recipeKey: String,
        defaultRecipe: Boolean,
        shared: Boolean
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}