package com.rubik.brewmoment.model.step

import com.rubik.brewmoment.model.GrindLevelEnum

class GrindStep(override val authorsTips: String, grindLevel: GrindLevelEnum): Step("grind") {
    override var description: String = "Grind your beans $grindLevel"
}