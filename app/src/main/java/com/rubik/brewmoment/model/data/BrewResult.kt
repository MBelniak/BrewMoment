package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum

class BrewResult(
    var brewTimeMinutes: Int = 0,
    var brewTimeSeconds: Int = 0,
    var equipment: EqTypeEnum = EqTypeEnum.AEROPRESS,
    var coffeeBlend: String = "",
    var notes: String = "",
    var isFavourite: Boolean = false,
    var date: Long = 0,
    var isRecipeDefault: Boolean = false,
    var isResultShared: Boolean = true,
    var key: String = "0",
    var recipeKey: String = "",
    val authorEmail: String = "",
    val author: String = ""
) {

}