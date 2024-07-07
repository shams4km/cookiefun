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
                    findNavController().navigate(R.id.action_recipesFragment_to_detailRecipeFragment)
                }
            )

            rvRecipes.adapter = adapter
            rvRecipes.layoutManager =LinearLayoutManager(requireContext())
        }
    }
}