package com.example.cookiefun.rvrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.cookiefun.R
import com.example.cookiefun.databinding.FragmentDetailRecipeBinding


class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {
    private var binding: FragmentDetailRecipeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailRecipeBinding.bind(view)

        val recipeName = arguments?.getString("recipeName")
        val recipeImageUrl = arguments?.getString("recipeImageUrl")
        val ingredients = arguments?.getStringArrayList("ingredients")
        val description = arguments?.getString("description")

        binding?.tvRecipeName?.text = recipeName
        binding?.tvRecipeDisc?.text = description

        binding?.ivRecipeImage?.load(recipeImageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }

        ingredients?.let {
            val listView = binding?.lvIngr
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
            listView?.adapter = adapter
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}