package com.example.exo2_tdm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val first = findViewById<TextView>(R.id.first)
        val second = findViewById<TextView>(R.id.second)
        val third= findViewById<TextView>(R.id.third)
        first.text=intent.getStringExtra("titre")
       second.text=intent.getStringExtra("date")
        third.text=intent.getStringExtra("desc")
    }
}
