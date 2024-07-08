package com.example.cookiefun

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categoriesFragment -> {
                    // Показать нижнюю навигацию при переходе в CategoriesFragment
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.recipesFragment2, R.id.detailRecipeFragment2 -> {
                    // Скрыть нижнюю навигацию при переходе в RecipesFragment и DetailRecipeFragment
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    // Во всех остальных случаях показывать нижнюю навигацию
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}
