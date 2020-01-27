package com.rubik.brewmoment.model.step

class PressAeroStep(override var authorsTips: String = "", timeInSeconds: Int = 0) : Step() {
    override var description: String = "Press the aeropress for $timeInSeconds seconds."
}