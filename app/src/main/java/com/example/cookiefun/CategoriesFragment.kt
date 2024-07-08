package com.example.cookiefun

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class CategoriesFragment : Fragment(R.layout.fragment_categories) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonPorridge: Button = view.findViewById(R.id.button_porridge)
        val buttonSalad: Button = view.findViewById(R.id.button_salad)
        val buttonSecond: Button = view.findViewById(R.id.button_second)
        val buttonSoup: Button = view.findViewById(R.id.button_soup)
        val buttonBakery: Button = view.findViewById(R.id.button_bakery)

        buttonSalad.setOnClickListener {
            val action = CategoriesFragmentDirections
                .actionCategoriesFragmentToRecipesFragment2("salad")
            findNavController().navigate(action)
        }

        buttonSecond.setOnClickListener {
            val action = CategoriesFragmentDirections
                .actionCategoriesFragmentToRecipesFragment2("second")
            findNavController().navigate(action)
        }

        buttonSoup.setOnClickListener {
            val action = CategoriesFragmentDirections
                .actionCategoriesFragmentToRecipesFragment2("soup")
            findNavController().navigate(action)
        }

        buttonBakery.setOnClickListener {
            val action = CategoriesFragmentDirections
                .actionCategoriesFragmentToRecipesFragment2("bakery")
            findNavController().navigate(action)
        }

        buttonPorridge.setOnClickListener {
            val action = CategoriesFragmentDirections
                .actionCategoriesFragmentToRecipesFragment2("porridge")
            findNavController().navigate(action)
        }
    }
}