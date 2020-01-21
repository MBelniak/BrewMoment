package com.rubik.brewmoment.model.step

class PourOverStep(override val authorsTips: String) : Step() {
    var timeInSeconds: Int = 0
    override val description: String = "Pour water over for $timeInSeconds seconds"
}