package com.example.cookiefun.rvrecipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiefun.databinding.ItemRecipeBinding

class RecipeAdapter(
    private val list: List<Recipe>,
    private val onClicked: (Recipe) -> Unit
): RecyclerView.Adapter<RecipeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClicked = onClicked)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.onBind(list[position])
    }
}