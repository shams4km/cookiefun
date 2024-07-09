package com.example.cookiefun.rvrecipes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookiefun.DbHelper
import com.example.cookiefun.R
import com.example.cookiefun.databinding.FragmentRecipesBinding


class RecipesFragment : Fragment(R.layout.fragment_recipes) {
    private var binding: FragmentRecipesBinding? = null
    private lateinit var dbHelper: DbHelper
    private var userId: Int? = null
    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipesBinding.bind(view)
        dbHelper = DbHelper(requireContext(), null)
        userId = getUserIdFromSharedPreferences()
        if (userId == null) {
            Log.e("RecipesFragment", "User ID is null. Please check the login process.")
        } else {
            Log.d("RecipesFragment", "User ID loaded: $userId")
        }

        val category = arguments?.getString("category") ?: "default_category"
        initAdapter(category)
        setCategoryTitle(category)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setCategoryTitle(category: String) {
        val title = when (category) {
            "salad" -> "Салаты"
            "second" -> "Вторые блюда"
            "soup" -> "Супы"
            "bakery" -> "Выпечка"
            "porridge" -> "Каши"
            else -> "Рецепты"
        }
        binding?.tvCategoryTitle?.text = title
    }

    private fun initAdapter(category: String) {
        binding?.run {
            val recipes = when (category) {
                "salad" -> RecipeRepository.salads
                "second" -> RecipeRepository.second
                "soup" -> RecipeRepository.soups
                "bakery" -> RecipeRepository.bakeries
                "porridge" -> RecipeRepository.porridge
                else -> emptyList()
            }

            adapter = RecipeAdapter(
                list = recipes,
                onClicked = { recipe ->
                    val action = RecipesFragmentDirections
                        .actionRecipesFragment2ToDetailRecipeFragment2(recipe)
                    findNavController().navigate(action)},
                onFavoriteClicked = { recipe -> // Добавляем обработчик для избранного
                    userId?.let { id ->
                        if (dbHelper.isFavorite(id, recipe.id)) {
                            dbHelper.removeFavorite(id, recipe.id)
                            Log.d("Test", "Remove Succesfull")
                            Toast.makeText(requireContext(), "${recipe.name} удален из избранного", Toast.LENGTH_SHORT).show()
                        } else {
                            dbHelper.addFavorite(id, recipe.id)
                            Toast.makeText(requireContext(), "${recipe.name} добавлен в избранное", Toast.LENGTH_SHORT).show()
                            Log.d("Test", "Add Succesfull")
                        }
                    }
                }
            )

            rvRecipes.adapter = adapter
            rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        }
    }
    private fun getUserIdFromSharedPreferences(): Int? {
        val sharedPreferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("user_id", -1)
        Log.d("RecipesFragment", "Loaded user_id from SharedPreferences: $userId")
        return userId.takeIf { it != -1 }
    }
}

