package com.rubik.brewmoment.model.step

class WaitStep(override var authorsTips: String = "", minutes: Int = 0, seconds: Int = 0) : Step() {
    override var description: String = "Wait until $minutes:$seconds."
}