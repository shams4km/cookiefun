package com.example.cookiefun.rvrecipes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookiefun.R
import com.example.cookiefun.databinding.FragmentRecipesBinding


class RecipesFragment : Fragment(R.layout.fragment_recipes) {
    private var binding: FragmentRecipesBinding? = null

    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipesBinding.bind(view)

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
                    findNavController().navigate(action)
                }
            )

            rvRecipes.adapter = adapter
            rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}