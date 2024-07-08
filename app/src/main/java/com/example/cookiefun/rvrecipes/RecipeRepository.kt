package com.example.cookiefun.rvrecipes

object RecipeRepository {
    val recipes: List<Recipe> = mutableListOf(
        Recipe(
            id = 1,
            name = "Recipe1",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321
        ),

        Recipe(
            id = 2,
            name = "Recipe1",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321
        ),

        Recipe(
            id = 3,
            name = "Recipe1",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321
        )
    )
}