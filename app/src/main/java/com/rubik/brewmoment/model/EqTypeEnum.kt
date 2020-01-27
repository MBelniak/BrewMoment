package com.rubik.brewmoment.model

enum class EqTypeEnum(val EqName: String) {
    AEROPRESS("Aeropress"), DRIP("Drip"), FRENCH_PRESS("French Press"), CHEMEX("Chemex");

    companion object {
        fun stringArray(): Array<String> {
            return Array(4) {
                EqTypeEnum.values()[it].EqName
            }
        }
    }
}