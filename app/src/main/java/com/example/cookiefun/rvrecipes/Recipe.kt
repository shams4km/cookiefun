package com.example.cookiefun.rvrecipes

data class Recipe (
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val description: String,
    val rating: Double,
    val url: String,
    val caloriesCount: Int
)
