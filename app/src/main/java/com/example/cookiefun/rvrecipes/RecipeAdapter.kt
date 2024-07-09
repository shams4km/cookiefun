package com.example.cookiefun.rvrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiefun.databinding.ItemRecipeBinding

class RecipeAdapter(
    private val list: List<Recipe>,
    private val onClicked: (Recipe) -> Unit,
    private val onFavoriteClicked: (Recipe) -> Unit
): RecyclerView.Adapter<RecipeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(binding, onClicked, onFavoriteClicked)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.onBind(list[position])
    }
}