package com.example.exo1tdm2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class MainActivity : AppCompatActivity() {
    val sharedPrefFile="com.example.exo1tdm2"

    var pst:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
    var a =0

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout= findViewById<RelativeLayout>(R.id.layout)
        val mPreferences=getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)

        val button =findViewById<Button>(R.id.button)
        val test2=findViewById<TextView>(R.id.test2)
        /*************Recuperate the shared data*************/

          pst=mPreferences.getInt("pst",0)

         updates(pst,layout)
        test2.text= "$pst"
        val intent2=Intent(this,Main2Activity::class.java)
/******Go to second Activity****/
        button.setOnClickListener{

            startActivity(intent2)

        }
        /***************Le choix du Spinner***********/
        val spinner: Spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

                //if no choice
            }


            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 if (a!=0){
                pst = position
                intent2.putExtra("pst1",pst)

                updates(pst,layout)

                 }
                a++

            }


        }



    }

  override  fun  onPause() {
      super.onPause()
      val mPreferences=getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
      val preferencesEditor = mPreferences.edit()
      preferencesEditor.putInt("pst",pst)
      preferencesEditor.apply()
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

