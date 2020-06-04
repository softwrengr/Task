package com.unitra.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unitra.task.adapter.ItemAdapter
import com.unitra.task.databinding.MainBinder
import com.unitra.task.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainBinder
    private lateinit var viewModel: MainViewModel
    private var adapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        setRecyclerView()
    }

    private fun setRecyclerView() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.getItems().observe(this,
            Observer { list ->
                binding.recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = ItemAdapter(list)
                }

            })

    }
}