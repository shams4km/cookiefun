package com.example.cookiefun.rvrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.cookiefun.R


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonPorridge: Button = view.findViewById(R.id.button_porridge)
        val buttonSalad: Button = view.findViewById(R.id.button_salad)
        val buttonSecond: Button = view.findViewById(R.id.button_second)
        val buttonSoup: Button = view.findViewById(R.id.button_soup)
        val buttonBakery: Button = view.findViewById(R.id.button_bakery)

        buttonSalad.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_recipesFragment2)
        }

        buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_recipesFragment2)
        }

        buttonSoup.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_recipesFragment2)
        }

        buttonBakery.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_recipesFragment2)
        }

        buttonPorridge.setOnClickListener {
            findNavController().navigate(R.id.action_categoriesFragment_to_recipesFragment2)
        }
    }
}