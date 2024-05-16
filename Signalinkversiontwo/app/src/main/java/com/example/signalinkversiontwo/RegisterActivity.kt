package com.example.signalinkversiontwo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.signalinkversiontwo.DbNeeds.DatabaseHelper
import com.example.signalinkversiontwo.DbNeeds.Hasher
import com.example.signalinkversiontwo.DbNeeds.User
import com.example.signalinkversiontwo.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    var users = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = DatabaseHelper(this)

        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBttn.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val name = binding.nameEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString()
            val passwordConfirm = binding.confirmPasswordEditText.text.toString()

            if (email != "" && name != "" && password != "" && passwordConfirm == password) {
                val id = db.getUsersCount() + 1
                val user = User(id, email, name, Hasher.hash(password))
                db.addUser(user)
                db.addLoggedInUser(user)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", user.id)
                startActivity(intent)
                finish()
            }
            else if (passwordConfirm != password) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
