package com.example.exo1tdm2

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main2)
        super.onCreate(savedInstanceState)
        var pst2:Int
        var a:Int=0
        val sharedPrefFile="com.example.exo1tdm2"
        val mPreferences=getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        pst2=mPreferences.getInt("pst",0)
        val layout= findViewById<RelativeLayout>(R.id.layout2)
        updates(pst2,layout)



        if (intent!= null && a!=0){

                pst2 = intent.getIntExtra("pst1",0)
              updates(pst2,layout)

        }
        a++
    }
    fun updates(position : Int,layout:RelativeLayout){


        if (position == 0){

            layout.setBackgroundColor(Color.RED)



        }

        if (position == 1){

            layout.setBackgroundColor(Color.BLUE)

        }

        if (position == 2){

            layout.setBackgroundColor(Color.GREEN)

        }


    }
}
