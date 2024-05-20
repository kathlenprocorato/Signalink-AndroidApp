package com.example.signalinkversiontwo.learnactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.signalinkversiontwo.R
import com.example.signalinkversiontwo.ui.learn.QuizFragment

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Load the QuizFragment into the container
        supportFragmentManager.beginTransaction()
            .replace(R.id.quiz_fragment_container, QuizFragment())
            .commit()
    }
}
