package com.example.cookiefun.rvrecipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.cookiefun.R
import com.example.cookiefun.databinding.FragmentDetailRecipeBinding


class DetailRecipeFragment : Fragment(R.layout.fragment_detail_recipe) {
    private var binding: FragmentDetailRecipeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailRecipeBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}