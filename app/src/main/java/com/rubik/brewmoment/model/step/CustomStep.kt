package com.rubik.brewmoment.model.step

class CustomStep(authorsTips: String = "") : Step() {
    override var authorsTips = authorsTips
        get() = ""
    override var description: String = authorsTips
}