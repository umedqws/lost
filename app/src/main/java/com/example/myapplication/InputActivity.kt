package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.myapplication.databinding.ActivityInputBinding
import kotlinx.coroutines.flow.MutableStateFlow

class InputActivity : AppCompatActivity() {
    lateinit var binding: ActivityInputBinding
    private var addLauncher: ActivityResultLauncher<Intent>? = null
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Проверка
        binding.okButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
                if (binding.inputNumber.text.toString() == "" && binding.inputPassword.text.toString() == "") {
                    startActivity(intent)
                }
                else {
                    Toast.makeText(
                        this,
                        "Неправльный пароль или номер телефона",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        binding.registr.setOnClickListener {
           val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}