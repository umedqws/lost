package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.myapplication.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private val listCity = listOf<String>("Душанбе","Норак","Вахдат","Хисор")
    lateinit var binding:ActivityRegistrationBinding
    lateinit var text:String
    @SuppressLint("WrongViewCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner:Spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,listCity)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                text = spinner.selectedItem.toString()
                val item = adapter.getItem(position)
            }
        }

        val namePut =binding.nameRegistration.text
        val numberPut =binding.numberRegistrtion.text


        binding.buttonregist.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
                intent.putExtra("number",numberPut)
                intent.putExtra("Name", namePut)
                intent.putExtra("city",text)
            startActivity(intent)

        }
        }
    }
