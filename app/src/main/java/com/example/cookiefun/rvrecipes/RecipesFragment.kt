package com.example.cookiefun.rvrecipes

import android.content.Context
import android.os.Bundle
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

        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initAdapter() {
        binding?.run {
            adapter = RecipeAdapter(
                list = RecipeRepository.recipes,
                onClicked = {
                    findNavController().navigate(R.id.action_recipesFragment2_to_detailRecipeFragment2)
                },
                onFavoriteClicked = { recipe -> // Добавляем обработчик для избранного
                    userId?.let { id ->
                        if (dbHelper.isFavorite(id, recipe.id)) {
                            dbHelper.removeFavorite(id, recipe.id)
                            Toast.makeText(requireContext(), "${recipe.name} удален из избранного", Toast.LENGTH_SHORT).show()
                        } else {
                            dbHelper.addFavorite(id, recipe.id)
                            Toast.makeText(requireContext(), "${recipe.name} добавлен в избранное", Toast.LENGTH_SHORT).show()
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
        return sharedPreferences.getInt("user_id", -1).takeIf { it != -1 }
    }
}