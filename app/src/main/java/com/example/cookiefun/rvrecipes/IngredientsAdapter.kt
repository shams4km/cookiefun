package com.example.cookiefun.rvrecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.cookiefun.R

class IngredientAdapter(context: Context, ingredients: List<String>) :
    ArrayAdapter<String>(context, 0, ingredients) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_ingredient, parent, false)
        val ingredient = getItem(position)
        val tvIngredient = view.findViewById<TextView>(R.id.tvIngredient)
        tvIngredient.text = ingredient
        return view
    }
}