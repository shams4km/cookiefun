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
            caloriesCount = 321,
            category = "salad"
        ),

        Recipe(
            id = 2,
            name = "Recipe2",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "porridge"
        ),

        Recipe(
            id = 3,
            name = "Recipe3",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "second"
        ),

        Recipe(
            id = 4,
            name = "Recipe4",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "bakery"
        ),

        Recipe(
            id = 5,
            name = "Recipe5",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "soup"
        )
    )

    val porridge: List<Recipe> = mutableListOf(
        Recipe(
            id = 2,
            name = "Recipe2",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "porridge"
        )
    )

    val salads: List<Recipe> = mutableListOf(
        Recipe(
            id = 1,
            name = "Recipe1",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "salad"
        )
    )

    val second: List<Recipe> = mutableListOf(
        Recipe(
            id = 3,
            name = "Recipe3",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "second"
        )
    )

    val bakeries: List<Recipe> = mutableListOf(
        Recipe(
            id = 4,
            name = "Recipe4",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "bakery"
        )
    )

    val soups: List<Recipe> = mutableListOf(
        Recipe(
            id = 5,
            name = "Recipe5",
            ingredients = mutableListOf("ing1", "ing2", "ing3"),
            description = "Desc1",
            rating = 5.0,
            url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_mABxooSjjVDxe2OyURHC4Ns-CnJzKM0jqA&s",
            caloriesCount = 321,
            category = "soup"
        )
    )
    fun getRecipeById(recipeId: Int): Recipe? {
        return recipes.find { it.id == recipeId }
    }
}