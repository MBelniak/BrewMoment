package com.rubik.brewmoment.model.step

class BoilWaterStep(override var authorsTips: String, temperature: Int) : Step() {
    override var description = "Heat the water up to $temperature\u00B0C"
}