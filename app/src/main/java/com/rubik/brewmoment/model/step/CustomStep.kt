package com.rubik.brewmoment.model.step

class CustomStep(override val authorsTips: String) : Step() {
    override val description: String = authorsTips
}