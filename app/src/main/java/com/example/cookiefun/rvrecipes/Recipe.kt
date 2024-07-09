package com.example.cookiefun.rvrecipes

import java.io.Serializable

data class Recipe (
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val description: String,
    val rating: Double,
    val url: String,
    val caloriesCount: Int,
    val category: String
) : Serializable