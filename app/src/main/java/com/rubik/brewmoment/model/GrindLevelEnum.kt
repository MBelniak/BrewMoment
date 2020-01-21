package com.rubik.brewmoment.model

enum class GrindLevelEnum(val grindLevel: String) {
    VERY_FINE("Very fine (aeropress)"), FINE("Fine (aeropress - drip V60"),
    MEDIUM("Medium (drip V60)"), COARSE("Coarse (drip V60 - Chemex"),
    VERY_COARSE("Very coarse (Chemex - French Press")
}