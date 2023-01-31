package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var bindings: ActivityAddBinding
    lateinit var addList: Lost

    private var image: String? = null

    private var addLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            bindings.imageAdd.setImageURI(it.data?.data)
            image = it.data?.data.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityAddBinding.inflate(layoutInflater)
        setContentView(bindings.root)
//Доступ к галерию
        bindings.image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            addLauncher.launch(gallery)
        }
        //Открыите активти Мейн
        bindings.listLost.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //ОТкрыте активти Проифл
        bindings.Profile.setOnClickListener {
            val intent = Intent(this,Profile::class.java)
            startActivity(intent)
            finish()
        }
        //Добавления в список
        bindings.buttonadd.setOnClickListener {
            val lost = Lost(
                image,
                bindings.nameAdd.text.toString(),
                bindings.descriptionAdd.text.toString(),
                bindings.telefonAdd.text.toString()
                )

            val addIntent = Intent().apply {
                putExtra("asd", lost)
            }
            setResult(RESULT_OK, addIntent)
            finish()
        }
    }
}