package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.likesButton).setOnClickListener {
            println("Clicked likesButton")
            (it as ImageView).setImageResource(R.drawable.ic_baseline_favorite_24)
        }
    }
}