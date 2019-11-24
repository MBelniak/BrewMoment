package com.rubik.brewmoment.model.step

class WaitStep(override var authorsTips: String, minutes: Int, seconds: Int) : Step() {
    override var description: String = "Wait until $minutes:$seconds"
}