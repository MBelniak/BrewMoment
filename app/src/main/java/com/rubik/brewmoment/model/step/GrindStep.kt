package com.rubik.brewmoment.model.step

import com.rubik.brewmoment.model.GrindLevelEnum
import java.text.NumberFormat

class GrindStep(override var authorsTips: String = "",
                grindLevel: GrindLevelEnum = GrindLevelEnum.MEDIUM,
                dose: Float = 0f): Step() {
    override var description: String = "grind your beans ${grindLevel.grindLevel[0].toLowerCase() + grindLevel.grindLevel.substring(1)} " +
            "using dose ${NumberFormat.getInstance().format(dose)}g/100ml."
}