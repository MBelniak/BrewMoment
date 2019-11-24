package com.rubik.brewmoment.model.step

class PressAeroStep(override var authorsTips: String, timeInSeconds: Int) : Step() {
    override var description: String = "Press the aeropress for $timeInSeconds seconds"
}