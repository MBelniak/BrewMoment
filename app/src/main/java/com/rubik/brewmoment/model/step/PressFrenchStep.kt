package com.rubik.brewmoment.model.step

class PressFrenchStep (override var authorsTips: String = "", timeInSeconds: Int = 0) : Step() {
    override var description: String = "Press the French Press for $timeInSeconds seconds."
}