package com.example.exo6_tdm2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.aditya.filebrowser.Constants
import com.aditya.filebrowser.FileChooser
import com.example.exo2_tdm2.SeanceDataBase
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File


class MainActivity : AppCompatActivity() {
var PICK_FILE_REQUEST:Int=1
    lateinit var db2: SeanceDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var text =findViewById<TextView>(R.id.text)
        val i2 = Intent(applicationContext, FileChooser::class.java)
        i2.putExtra(Constants.SELECTION_MODE, Constants.SELECTION_MODES.SINGLE_SELECTION.ordinal)
        startActivityForResult(i2, PICK_FILE_REQUEST)




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
