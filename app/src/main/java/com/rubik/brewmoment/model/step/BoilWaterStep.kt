package com.rubik.brewmoment.model.step

class BoilWaterStep(override val authorsTips: String, temperature: Int) : Step() {
    override val description = "Heat the water up to $temperature\u00B0C"
}