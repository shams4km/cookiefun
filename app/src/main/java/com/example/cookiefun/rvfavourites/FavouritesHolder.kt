package com.example.cookiefun.rvfavourites

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookiefun.databinding.ItemRecipeBinding
import com.example.cookiefun.rvrecipes.Recipe

class FavouritesHolder(
    private val binding: ItemRecipeBinding,
    private val onClicked: (Recipe) -> Unit,
    private val onFavoriteClicked: (Recipe, Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(recipe: Recipe) {
        binding.run {
            ivRecipeImage.load(recipe.url)
            tvRecipeName.text = recipe.name
            root.setOnClickListener {
                onClicked.invoke(recipe)
            }
            ivFavorite.setOnClickListener {
                val isFavorite = ivFavorite.tag as? Boolean ?: false
                onFavoriteClicked.invoke(recipe, isFavorite)
                ivFavorite.tag = !isFavorite
            }
        }
    }
}