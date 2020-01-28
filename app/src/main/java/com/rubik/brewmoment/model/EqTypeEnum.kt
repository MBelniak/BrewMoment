package com.rubik.brewmoment.model

enum class EqTypeEnum(val EqName: String) {
    AEROPRESS("Aeropress"), DRIP("Drip"), CHEMEX("Chemex"), FRENCH_PRESS("French Press");

    companion object {
        fun stringArray(): Array<String> {
            return Array(4) {
                values()[it].EqName
            }
        }
    }
}