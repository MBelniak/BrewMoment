package com.rubik.brewmoment.model.data

import java.sql.Date

class BrewResult(
    val brewTimeMinutes: Int,
    val brewTimeSeconds: Int,
    val coffeeBlend: String,
    val notes: String,
    val isFavourite: Boolean,
    val date: Date,
    var isRecipeDefault: Boolean,
    var isResultShared: Boolean,
    var key: String = "0",
    var recipeKey: String? = null
) {

}