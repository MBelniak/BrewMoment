package com.rubik.brewmoment.model.step

import com.beust.klaxon.TypeAdapter
import kotlin.reflect.KClass

class StepTypeAdapter: TypeAdapter<Step> {
    override fun classFor(type: Any): KClass<out Step> = when(type as String) {
        "boil" -> BoilWaterStep::class
        "custom" -> CustomStep::class
        "grind" -> GrindStep::class
        "pour" -> PourOverStep::class
        "press" -> PressAeroStep::class
        "stir" -> StirStep::class
        "wait" -> WaitStep::class
        else -> throw IllegalArgumentException("Unknown type: $type")
    }
}