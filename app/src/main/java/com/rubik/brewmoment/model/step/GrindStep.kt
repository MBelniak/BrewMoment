package com.rubik.brewmoment.model.step

import com.rubik.brewmoment.model.GrindLevelEnum

class GrindStep(override var authorsTips: String = "", grindLevel: GrindLevelEnum = GrindLevelEnum.MEDIUM): Step() {
    override var description: String = "Grind your beans $grindLevel"
}