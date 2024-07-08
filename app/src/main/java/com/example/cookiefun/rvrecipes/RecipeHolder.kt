package com.example.cookiefun.rvrecipes

import android.util.Printer
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.cookiefun.databinding.ItemRecipeBinding

class RecipeHolder(
    private val binding: ItemRecipeBinding,
    private val onClicked: (Recipe) -> Unit,
    private val onFavoriteClicked: (Recipe) -> Unit
): ViewHolder(binding.root) {

    fun onBind(recipe: Recipe) {
        binding.run {
            ivRecipeImage.load(recipe.url)
            tvRecipeName.text = recipe.name
            root.setOnClickListener {
                onClicked.invoke(recipe)
            }
            ivFavorite.setOnClickListener {
                onFavoriteClicked.invoke(recipe)
            }
        }
    }
}