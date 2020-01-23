package com.rubik.brewmoment.model.step

class WaitStep(override val authorsTips: String, minutes: Int, seconds: Int) : Step() {
    override var description: String = "Wait until $minutes:$seconds"
}