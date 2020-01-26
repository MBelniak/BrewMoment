package com.rubik.brewmoment.model.step

class CustomStep(override val authorsTips: String) : Step("custom") {
    override var description: String = authorsTips
}