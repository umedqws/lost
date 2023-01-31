package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    lateinit var bindings:ActivityProfileBinding
    private var image: String? = null
    lateinit var name: String
    lateinit var number: String

    private var addImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            bindings.imageProfile.setImageURI(it.data?.data)
            image = it.data?.data.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(bindings.root)

        //Доступ к галерию
        bindings.imageBackProfile.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            addImage.launch(gallery)
        }

        bindings.rcViewProfile.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
       val adapter = MyAdapter()
        adapter.submitList(Repository.list)
        adapter.notifyDataSetChanged()
        bindings.rcViewProfile.adapter =adapter

                //Добавления данные с RegistrationActivity в ProfileActivity
                name = intent.extras?.get("Name").toString()
                number = intent.extras?.get("number").toString()
                val city = intent.getStringExtra("city")
            bindings.nameProfile.text = name.toString()
            bindings.numberProfile.text = number.toString()
            bindings.cityProfile.text = city



//Open MainActivity or listLost
        bindings.listLost.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

//Open Addactivity
        bindings.add.setOnClickListener {
            val intent = Intent(this,AddActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}

