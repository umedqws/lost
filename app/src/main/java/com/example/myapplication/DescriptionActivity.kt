package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide

class DescriptionActivity : AppCompatActivity() {
    lateinit var dataLost: Lost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        dataLost = intent.extras?.get("des") as Lost
        val img = findViewById<ImageView>(R.id.imageLost)
        val naem = findViewById<TextView>(R.id.nameLost)
        val descriptionText = findViewById<TextView>(R.id.description)
        val call = findViewById<TextView>(R.id.callPhone)
        naem.text = dataLost.name
        Glide
            .with(this)
            .load(dataLost.img)
            .centerCrop()
            .into(img)
        descriptionText.text = dataLost.discription

        val whatsApp = findViewById<TextView>(R.id.whatsApp)

        // url ссылка для WhatsApp
        val url = "https://api.whatsapp.com/send?phone=wa.me/+992$${dataLost.telefon.toString()}"
        // Открыте приложеиня WhatsApp для чата
        whatsApp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }

        //Открыте приложения "Телефое" для звонка
        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${dataLost.telefon.toString()}")
            startActivity(intent)
        }
    }
}