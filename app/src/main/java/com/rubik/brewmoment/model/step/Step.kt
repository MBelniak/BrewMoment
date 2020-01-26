package com.rubik.brewmoment.model.step

import com.beust.klaxon.TypeFor

@TypeFor(field = "type", adapter = StepTypeAdapter::class)
abstract class Step(val type: String) {
    var recipeKey: Int = 0

    abstract var description: String
    abstract val authorsTips: String
}




