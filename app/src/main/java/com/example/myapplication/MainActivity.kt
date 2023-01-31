package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewsecond: RecyclerView
    private var addLauncher: ActivityResultLauncher<Intent>? = null
    lateinit var name: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val addButton = binding.add
        //Откритиые AddActivity
        addButton.setOnClickListener {
            addLauncher?.launch(Intent(this, AddActivity::class.java))

        }
        //Данные с друго активти (Активти для добоавления)
        addLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                //Добавления Элемент в список
                val newList = mutableListOf<Lost>()
                for (i in adapter.currentList) {
                    newList.add(i)
                }
                newList.add((it.data?.getSerializableExtra("asd") as Lost))
                adapter.submitList(newList)
            }
        }

        //Соединие адаптера и RcView
        recyclerView = binding.RcView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter()
        adapter.submitList(Repository.list)
        recyclerView.adapter = adapter
        recyclerViewsecond = binding.RcViewSecond
        recyclerViewsecond.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter.OnClickItem = {
            val intent = Intent(this, DescriptionActivity::class.java)
            Log.d("TAG_TEST", "onCreate: ${it.img}")
            intent.putExtra("des", it)
            startActivity(intent)
        }

        val secondadapter = SecondAdapter()
        secondadapter.onItemClick = { index: Int ->
            secondadapter.submitList(categoryData(index))

            when (index) {
                0 -> adapter.submitList(Repository.list)
                1 -> adapter.submitList(Found.list)
            }
        }
        secondadapter.submitList(categoryData())
        recyclerViewsecond.adapter = secondadapter


        //Open Profile
        binding.Profile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)

        }
    }


    private fun categoryData(indexOfSelectedElement: Int? = null): MutableList<Categor> {
        val list = mutableListOf(
            Categor(1, "Потерянный"),
            Categor(2, "Найденный")
        )
        list.forEachIndexed { index, Categor ->
            if (index == indexOfSelectedElement) {
                Categor.isSelected = true
            }
        }
        return list
    }
}

