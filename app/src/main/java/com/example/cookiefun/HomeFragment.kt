package com.example.cookiefun


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookiefun.rvrecipes.Recipe
import com.example.cookiefun.rvrecipes.RecipeRepository
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private var itemList = mutableListOf<Recipe>()
    private val filteredList = mutableListOf<Recipe>()
    private var randomRecipe: Recipe? = null


    private lateinit var recipeOfDayImageView: ImageView
    private lateinit var recipeOfDayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var i = 0
        while (i < RecipeRepository.recipes.size) {
            itemList.add(RecipeRepository.recipes[i])
            i++
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recipeOfDayImageView = view.findViewById(R.id.recipeOfDayImageView)
        recipeOfDayTextView = view.findViewById(R.id.recipeOfDayTextView)


        adapter = MyAdapter(filteredList, object : OnItemClickListener {
            override fun onItemClick(recipe: Recipe) {
                navigateToDetail(recipe)
            }
        })
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        updateRecipeOfDay()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main_title)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recipeOfDayImageView: ImageView = view.findViewById(R.id.recipeOfDayImageView)
        recipeOfDayImageView.setOnClickListener {
            onRecipeOfDayClicked(it)
        }
    }

    private fun filterList(query: String?) {
        filteredList.clear()
        if (!query.isNullOrBlank()) {
            val lowerCaseQuery = query.lowercase(Locale.getDefault())
            for (recipe in itemList) {
                if (recipe.name.lowercase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    filteredList.add(recipe)
                }
            }
        }
        adapter.notifyDataSetChanged()

        if (filteredList.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.GONE
        }
    }

    private fun navigateToDetail(recipe: Recipe) {
        val bundle = Bundle().apply {
            putString("recipeName", recipe.name)
            putString("recipeImageUrl", recipe.url)
            putStringArrayList("ingredients", ArrayList(recipe.ingredients))
            putString("description", recipe.description)
        }
        findNavController().navigate(R.id.action_homeFragment_to_detailRecipeFragment2, bundle)
    }

    private fun updateRecipeOfDay() {
        randomRecipe = RecipeRepository.recipes.random()
        recipeOfDayImageView.load(randomRecipe?.url) {
            placeholder(R.drawable.rounded_corners_background)
            crossfade(true)
        }
        recipeOfDayTextView.text = randomRecipe?.name

    }

    fun onRecipeOfDayClicked(view: View) {
        val bundle = Bundle().apply {
            putString("recipeName", randomRecipe?.name)
            putString("recipeImageUrl", randomRecipe?.url)
            putStringArrayList("ingredients", ArrayList(randomRecipe?.ingredients))
            putString("description", randomRecipe?.description)
        }
        findNavController().navigate(R.id.action_homeFragment_to_detailRecipeFragment2, bundle)
    }


    inner class MyAdapter(
        private val items: List<Recipe>,
        private val itemClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(recipe: Recipe) {
                val textView = itemView.findViewById<TextView>(android.R.id.text1)
                textView.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_white))
                textView.text = recipe.name
                itemView.setOnClickListener {
                    itemClickListener.onItemClick(recipe)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(recipe: Recipe)
    }

}
