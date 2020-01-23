package com.rubik.brewmoment.model.step

class PourOverStep(override val authorsTips: String) : Step() {
    var timeInSeconds = 0
    var milliliters = 0
    override var description: String = "Pour ${milliliters}ml of water for $timeInSeconds seconds"
}