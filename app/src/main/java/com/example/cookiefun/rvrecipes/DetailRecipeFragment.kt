package com.example.cookiefun.rvrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.cookiefun.R
import com.example.cookiefun.databinding.FragmentDetailRecipeBinding


class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {
    private var binding: FragmentDetailRecipeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailRecipeBinding.bind(view)

        val args: DetailRecipeFragmentArgs by navArgs()
        val recipe = args.recipe

        binding?.apply {
            tvRecipeName.text = recipe.name
            tvRecipeDisc.text = recipe.description
            ivRecipeImage.load(recipe.url)

            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                recipe.ingredients
            )
            lvIngr.adapter = adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}