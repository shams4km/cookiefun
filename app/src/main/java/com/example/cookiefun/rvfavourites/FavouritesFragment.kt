// FavouritesFragment.kt
package com.example.cookiefun.rvfavourites

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiefun.DbHelper
import com.example.cookiefun.R
import com.example.cookiefun.rvrecipes.RecipeRepository
import com.example.cookiefun.rvrecipes.RecipesFragmentDirections

class FavouritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavouritesAdapter
    private lateinit var dbHelper: DbHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        recyclerView = view.findViewById(R.id.rvFavourites)

        // Инициализация RecyclerView и Adapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = FavouritesAdapter(emptyList(), { recipe ->
            // Здесь мы вызываем фрагмент с деталями рецепта
            val action = FavouritesFragmentDirections
                .actionFavouritesFragmentToDetailRecipeFragment2(recipe)
            findNavController().navigate(action)
        }, { recipe, isFavorite ->
            // Обработка добавления/удаления из избранного
            val userId = getCurrentUserId()
            if (userId != null) {
                dbHelper.removeFavorite(userId, recipe.id)
                loadFavoriteRecipes(userId)
                Toast.makeText(
                    requireContext(),
                    "${recipe.name} удален из избранного",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        recyclerView.adapter = adapter

        dbHelper = DbHelper(requireContext(), null)

        // Получение избранных рецептов для текущего пользователя (замените userId на реальный id пользователя)
        val userId = getCurrentUserId() // например, получение текущего пользователя из сессии или SharedPreferences
        if (userId != null) {
            loadFavoriteRecipes(userId)
        }

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

    private fun getCurrentUserId(): Int? {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("user_id", -1)
        Log.d("RecipesFragment", "Loaded user_id from SharedPreferences: $userId")
        return userId.takeIf { it != -1 }
    }
}