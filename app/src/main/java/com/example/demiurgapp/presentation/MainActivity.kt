package com.example.demiurgapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demiurgapp.R
import com.example.demiurgapp.databinding.ActivityMainBinding
import com.example.demiurgapp.domain.entities.Cell

class MainActivity : AppCompatActivity() {

    private lateinit var listAdapter: RvAdapter
    private var binding: ActivityMainBinding? = null
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        addListItem()
        setupAdapter()

    }

    private fun setupAdapter() {
        listAdapter = RvAdapter()
        binding?.recyclerviewMain?.adapter = listAdapter

        binding?.recyclerviewMain?.layoutManager = LinearLayoutManager(this)

        mainViewModel.observeList()
        mainViewModel.mainListLife.observe(this) { list ->
            listAdapter.submitList(list) {
                if (list.isNotEmpty()) {
                    binding?.recyclerviewMain?.smoothScrollToPosition(list.size - 1)
                }
            }
        }
    }

    private fun addListItem() {
        binding?.buttonCreate?.setOnClickListener {
            mainViewModel.addItem(Cell.randomCell())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}