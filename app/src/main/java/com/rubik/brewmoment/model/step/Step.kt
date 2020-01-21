package com.rubik.brewmoment.model.step

abstract class Step {
    var recipeKey: Int = 0

    abstract val description: String
    abstract val authorsTips: String
}




