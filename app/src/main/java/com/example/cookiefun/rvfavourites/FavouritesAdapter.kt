package com.example.cookiefun.rvfavourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiefun.databinding.ItemRecipeBinding
import com.example.cookiefun.rvrecipes.Recipe

class FavouritesAdapter(
    private var list: List<Recipe>,
    private val onClicked: (Recipe) -> Unit,
    private val onFavoriteClicked: (Recipe, Boolean) -> Unit
) : RecyclerView.Adapter<FavouritesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouritesHolder(binding, onClicked, onFavoriteClicked)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavouritesHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun setRecipes(recipes: List<Recipe>) {
        list = recipes
        notifyDataSetChanged()
    }
}