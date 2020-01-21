package com.rubik.brewmoment.model.step

class WaitStep(override val authorsTips: String, minutes: Int, seconds: Int) : Step() {
    override val description: String = "Wait until $minutes:$seconds"
}