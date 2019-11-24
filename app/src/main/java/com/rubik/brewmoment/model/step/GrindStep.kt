package com.rubik.brewmoment.model.step

class GrindStep(override var authorsTips: String): Step() {
    var grindLevel: String = ""
    override var description: String = "Grind your beans $grindLevel"
}