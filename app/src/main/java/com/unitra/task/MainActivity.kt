package com.unitra.task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unitra.task.adapter.ItemAdapter
import com.unitra.task.callbacks.ClickListener
import com.unitra.task.databinding.MainBinder
import com.unitra.task.models.ItemModel
import com.unitra.task.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {
    val UNLOCK_REQUEST = 1
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
                    adapter = ItemAdapter(list, callBack)
                }
            })
    }

    private val callBack = object : ClickListener {
        override fun onUpdate(item: ItemModel, position: Int) {
            if (item.lock == 1) {
                startActivityForResult(Intent(this@MainActivity, SecondActivity::class.java), 1)
            } else {
                Toast.makeText(this@MainActivity, "item is lock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UNLOCK_REQUEST && resultCode == Activity.RESULT_OK) {
            val checkUnlock = data?.getBooleanExtra("unlock", false)
            if (checkUnlock!!) {
                viewModel.updateItem(ItemModel(1, "item 2", 1))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_items -> {
                viewModel.deleteAll()
                true
            }
            R.id.insert_items -> {
                viewModel.setItems()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}