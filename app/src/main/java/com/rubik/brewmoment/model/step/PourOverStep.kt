package com.rubik.brewmoment.model.step

class PourOverStep(override var authorsTips: String = "", timeInSeconds: Int = 0, milliliters: Int = 0) : Step() {
    override var description: String = "Pour ${milliliters}ml of water for $timeInSeconds seconds."
}