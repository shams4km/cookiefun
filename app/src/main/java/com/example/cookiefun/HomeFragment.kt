package com.example.cookiefun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private val itemList = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grape")
    private val filteredList = mutableListOf<String>()
    private lateinit var noResultsTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_home2, container, false)
        val view = inflater.inflate(R.layout.fragment_home2, container, false)
        searchView = view.findViewById(R.id.searchView)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter(filteredList)
        recyclerView.adapter = adapter
        noResultsTextView = view.findViewById(R.id.noResultsTextView)

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main_title)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun filterList(query: String?) {
        filteredList.clear()
        if (!query.isNullOrBlank()) {
            val lowerCaseQuery = query.lowercase(Locale.getDefault())
            for (item in itemList) {
                if (item.lowercase(Locale.getDefault()).contains(lowerCaseQuery)) {
                    filteredList.add(item)
                }
            }
        }
        adapter.notifyDataSetChanged()

        if (filteredList.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            noResultsTextView.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            noResultsTextView.visibility = View.VISIBLE
        }

        //recyclerView.visibility = if (filteredList.isNotEmpty()) View.VISIBLE else View.GONE
    }
    inner class MyAdapter(private val items: List<String>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: String) {
                itemView.findViewById<TextView>(android.R.id.text1).text = item
            }
        }
    }
}
