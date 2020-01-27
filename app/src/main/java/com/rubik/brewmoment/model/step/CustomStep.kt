package com.rubik.brewmoment.model.step

class CustomStep(override var authorsTips: String = "") : Step() {
    override var description: String = authorsTips
}