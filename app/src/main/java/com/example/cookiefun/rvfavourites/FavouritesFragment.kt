// FavouritesFragment.kt
package com.example.cookiefun.rvfavourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiefun.DbHelper
import com.example.cookiefun.R
import com.example.cookiefun.rvrecipes.RecipeRepository

class FavouritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter
    private lateinit var dbHelper: DbHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        recyclerView = view.findViewById(R.id.rvFavourites)

        // Инициализация RecyclerView и Adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = RecipeAdapter(emptyList())
        recyclerView.adapter = adapter

        dbHelper = DbHelper(requireContext(), null)

        // Получение избранных рецептов для текущего пользователя (замените userId на реальный id пользователя)
        val userId = getCurrentUserId() // например, получение текущего пользователя из сессии или SharedPreferences
        loadFavoriteRecipes(userId)

        return view
    }

    private fun loadFavoriteRecipes(userId: Int) {
        Thread {
            val favoriteRecipeIds = dbHelper.getFavoriteRecipes(userId)
            val favoriteRecipes = favoriteRecipeIds.mapNotNull { recipeId ->
                RecipeRepository.getRecipeById(recipeId)
            }

            activity?.runOnUiThread {
                adapter.setRecipes(favoriteRecipes)
            }
        }.start()
    }

    private fun getCurrentUserId(): Int {
        // Вернуть реальный id текущего пользователя
        return 1 // Например, временно возвращаем какой-то id
    }
}
