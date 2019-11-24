package com.rubik.brewmoment.model.step

class PourOverStep(override var authorsTips: String) : Step() {
    var timeInSeconds: Int = 0
    override var description: String = "Pour water over for $timeInSeconds seconds"
}