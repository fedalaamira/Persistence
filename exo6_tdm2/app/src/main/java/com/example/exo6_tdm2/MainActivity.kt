package com.example.exo6_tdm2

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.aditya.filebrowser.Constants
import com.aditya.filebrowser.FileChooser
import com.example.exo2_tdm2.SeanceDataBase
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.util.*
import java.util.Calendar.DATE
import kotlin.math.absoluteValue

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class MainActivity : AppCompatActivity() {
    var PICK_FILE_REQUEST: Int = 1
    lateinit var db2: SeanceDataBase
    var choix: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val i2 = Intent(applicationContext, FileChooser::class.java)
        i2.putExtra(Constants.SELECTION_MODE, Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal)
        startActivityForResult(i2, PICK_FILE_REQUEST)

        val intent2 = Intent(this, Main2Activity::class.java)
        var button = findViewById<Button>(R.id.button)
        var button2 = findViewById<Button>(R.id.button2)
        var button3 = findViewById<Button>(R.id.button3)
        var button4 = findViewById<Button>(R.id.button4)
        var button5 = findViewById<Button>(R.id.button5)
        var text = findViewById<EditText>(R.id.mdl)
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var mois = c.get(Calendar.MONTH)
        var day2 = c.get(Calendar.DAY_OF_WEEK)

        var day = c.get(Calendar.DAY_OF_MONTH)

        //  var listall=db2.daoSeance().findBymodule("tdm")


        button.setOnClickListener {
            choix = 1
            intent2.putExtra("text", text.text.toString())
            intent2.putExtra("pst", choix)
            startActivity(intent2)

        }
        button2.setOnClickListener {
            choix = 2
            intent2.putExtra("pst", choix)
            intent2.putExtra("text", text.text.toString())
            startActivity(intent2)

        }
        button3.setOnClickListener {
            choix = 3
            intent2.putExtra("pst", choix)
            intent2.putExtra("text", text.text.toString())
            startActivity(intent2)

        }
        button4.setOnClickListener {
            choix = 4
            intent2.putExtra("pst", choix)
            val datePickerDialog = DatePickerDialog(
                this,
                R.style.AppTheme,
                DatePickerDialog.OnDateSetListener { test, mYear, mMonth, dayOfMonth ->

                    day = dayOfMonth
                    mois = mMonth
                    year = mYear

                    intent2.putExtra("day", day.toInt())
                    intent2.putExtra("mois", mois.toInt())
                    intent2.putExtra("year", year.toInt())
                    startActivity(intent2)



                },
                year,
                mois,
                day
            )

            datePickerDialog.show()

        }
        button5.setOnClickListener {
            choix = 5
            intent2.putExtra("pst", choix)
            val datePickerDialog = DatePickerDialog(
                this,
                R.style.AppTheme,
                DatePickerDialog.OnDateSetListener { test, mYear, mMonth, dayOfWeek ->

                    day2 = dayOfWeek
                    mois = mMonth
                    year = mYear
                    c.set(Calendar.MONTH, mMonth)
                    c.set(Calendar.YEAR, mYear)
                    c.set(Calendar.DAY_OF_WEEK,day2)
                    val dt = c.getTime()
                    c.setTime(dt)
                    val dayOfWeek = c.get(Calendar.DAY_OF_WEEK)
                    intent2.putExtra("dayweek", day2.toInt())
                    intent2.putExtra("moisweek", mois.toInt())
                    intent2.putExtra("yearweek", year.toInt())
                    intent2.putExtra("nbweek", dayOfWeek.toInt())
                    startActivity(intent2)

Log.w("testweek",dayOfWeek.toString())

                },
                year,
                mois,
                day2
            )

            datePickerDialog.show()

        }

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        db2= SeanceDataBase.getDatabase(this)
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {

            var d = data!!.data.toString()
            var file = File(d.subSequence(5,d.length).toString())
            val bufferedReader: BufferedReader = file.bufferedReader()
            var inputString = bufferedReader.use { it.readText()
                }
            var obj = JSONObject(inputString)
            var jsonArray: JSONArray = obj.getJSONArray("seances")
            var gson = Gson()
            var jsonObject: JSONObject = JSONObject()

            //transformer la réponse en une liste de seances
            for (i in 0 until jsonArray.length()) {
                jsonObject = jsonArray.getJSONObject(i)


                db2?. daoSeance()?.insertAll(gson.fromJson(jsonObject.toString(), Seance::class.java))



            }

        }
    }

}
