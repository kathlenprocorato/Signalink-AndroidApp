package com.example.signalink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val translateButton = findViewById<ImageButton>(R.id.translateButton)
        translateButton.setOnClickListener {
            val intent = Intent(this, TranslateActivity::class.java)
            startActivity(intent)
        }

        val dictionaryButton = findViewById<ImageButton>(R.id.dictionaryButton)
        dictionaryButton.setOnClickListener {
            val intent = Intent(this, ASLDictionaryActivity::class.java)
            startActivity(intent)
        }

    }
}