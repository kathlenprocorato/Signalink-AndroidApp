package com.example.signalinkversiontwo.learnactivity

<<<<<<< HEAD
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.signalinkversiontwo.R

class SimplePhrasesActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simplephrases)

        // Find reference to each button
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        // Add references for other buttons

        // Set OnClickListener for each button
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button1 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.good))
            R.id.button2 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.hello))
            R.id.button3 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.morning))
            R.id.button4 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.name))
            R.id.button5 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.please))
            R.id.button6 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.thankyou))
            R.id.button7 -> showVideoModal(Uri.parse("android.resource://" + packageName + "/" + R.raw.what))
        }
    }

    private fun showVideoModal(videoUri: Uri) {
        val fragment = VideoModalFragment.newInstance(videoUri)
        fragment.show(supportFragmentManager, "VideoModalFragment")
    }
}
=======
import androidx.appcompat.app.AppCompatActivity

class SimplePhrasesActivity : AppCompatActivity() {
}
>>>>>>> 2243e2e1d5060d319043e4890a120f5a7aa7475a
