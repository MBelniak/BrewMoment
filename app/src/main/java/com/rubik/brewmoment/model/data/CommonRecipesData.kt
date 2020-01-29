package com.rubik.brewmoment.model.data

import com.rubik.brewmoment.model.EqTypeEnum
import com.rubik.brewmoment.model.GrindLevelEnum
import com.rubik.brewmoment.model.step.*

object CommonRecipesData {

//    fun getAll(context: Context): List<Recipe>
//    {
//        val arr = arrayListOf<Recipe>()
//        val inputStream = context.resources.openRawResource(R.raw.common_recipes)
//        val result: List<Recipe>? = Klaxon().parseArray(inputStream)
//        if (result != null) {
//            for(obj in result) {
//                arr.add(obj)
//            }
//        }
//        return arr
//    }

    private val recipeList = arrayListOf(Recipe(
        "The Ultimate V60 Technique", "James Hoffmann", "",
        "Universal drip recipe published on James' YouTube channel.",
        EqTypeEnum.DRIP, 3, 30,
        GrindLevelEnum.FINE, 95f, 30f,
        totalWater = 500f, steps = listOf(
            CustomStep("Rinse paper filter with water just off the boil."),
            CustomStep("Put grounds to V60 and create well in the middle of them."),
            PourOverStep("Then, swirl the dripper.", 10, 60),
            WaitStep("", 0, 45),
            PourOverStep("", 30, 240),
            WaitStep("", 1, 15),
            StirStep("Once anti-clockwise, once clockwise, with a spoon."),
            PourOverStep("", 30, 200),
            WaitStep("Let the water draw down.", 3,     30)),
        isShared = false, isDefault = true, key = "0")
    , Recipe(
            "Sottt Rao's V60 method", "Scott Rao", "",
            "Drip V60 recipe published on Scott's YouTube channel.",
            EqTypeEnum.DRIP, 3, 0,
            GrindLevelEnum.MEDIUM, 95f,  22f, totalWater = 360f, steps =
            listOf(
                CustomStep("Rinse paper filter with water just off the boil."),
                CustomStep("Put grounds to V60."),
                PourOverStep("Then, excavate the coffee with a spoon.", 10, 66),
                WaitStep("", 0, 45),
                PourOverStep("", 30, 294),
                StirStep("One gentle rotation."),
                WaitStep("Let the water draw down.", 3, 0)),
            isShared = false, isDefault = true, key = "1")
    , Recipe(
                "The Ultimate French Press Technique", "James Hoffmann", "",
            "Simple and universal Franch Press method.",
            EqTypeEnum.FRENCH_PRESS, 3, 0,
            GrindLevelEnum.COARSE, 97f, 30f, totalWater = 500f, steps =
            listOf(
                CustomStep("Put grounds int to Franch Press."),
                PourOverStep("", 30, 500),
                WaitStep("", 4, 0),
                StirStep("Stir crust and then scoop up foam and coffee bits off the top."),
                WaitStep("Wait 5-8 minutes. You can make a breakfast.", 8, 0),
                PressFrenchStep("Press just to the edge of the drink.", 0)),
            isShared = false, isDefault = true, key = "2"
        )
    , Recipe(
            "The Chemex technique", "James Hoffmann", "",
            "Simple and universal chemex method",
            EqTypeEnum.CHEMEX, 4, 0,
            GrindLevelEnum.COARSE, 95f, 30f, totalWater = 500f, steps =
            listOf(
                CustomStep("Rinse paper filter with water just off the boil."),
                CustomStep("Put grounds to the chemex and create well in the middle of them."),
                PourOverStep("Then, swirl the chemex.", 15, 60),
                WaitStep("", 0, 45),
                PourOverStep("", 30, 240),
                WaitStep("", 1, 30),
                StirStep("Once anti-clockwise, once clockwise, with a spoon."),
                PourOverStep("", 30, 200),
                WaitStep("Let the water draw down.", 4,     0)),
            isShared = false, isDefault = true, key = "3")
    , Recipe(
            "Tim's Aeropress method", "Tim Wendelboe", "",
            "Basic Aeropress recipe from the coffee roasting champion.",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.VERY_FINE, 90f, 14f, totalWater = 200f, steps =
            listOf(
                CustomStep("Set the Aeropress for a non-inverted method."),
                CustomStep("Rinse the filter."),
                CustomStep("Put grounds into the Aeropress. Start timer."),
                PourOverStep("", 10, 200),
                StirStep("Stir 3 times. Clog the Aeropress with the plunger."),
                WaitStep("", 1, 0),
                StirStep("Stir once"),
                PressAeroStep("", 25)),
            isShared = false, isDefault = true, key = "4"
        ))

    fun getAll(): List<Recipe>
    {
        return recipeList
    }

    fun getByKey(key: String): Recipe
    {
        if (key.toInt() < recipeList.size)
            return recipeList[key.toInt()]
        return getDefaultRecipe()
    }

    fun getDefaultRecipe(): Recipe {
        return Recipe(
            "Sample Recipe", "Noone", "", "Sample description",
            EqTypeEnum.AEROPRESS, 3, 0,
            GrindLevelEnum.MEDIUM, 90f, 0f, totalWater = 0f, steps = listOf(WaitStep("", 1, 30),
                WaitStep("aaa", 1, 30),
                WaitStep("aaa", 1, 30)), isShared = false, isDefault = true
        )
    }
}