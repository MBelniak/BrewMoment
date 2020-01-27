package com.rubik.brewmoment.model.step

import java.text.NumberFormat

class BoilWaterStep(override var authorsTips: String = "", temperature: Float = 0f) : Step() {
    override var description = "Heat the water up to ${NumberFormat.getInstance().format(temperature)}\u00B0C."
}