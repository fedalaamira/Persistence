package com.example.exo2_tdm2

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tp4_exo3.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var pst: Int = 0
    var position:Int=0
    var positionL:Int=1
    lateinit var layoutManager : LinearLayoutManager
    var itemList : MutableList<Note> = ArrayList()
    lateinit var list : MutableList<Int>
    lateinit var adapter: RecyclerAdapter
     lateinit var db:NoteDataBase
   lateinit var intent2:Intent
    override fun onCreate(savedInstanceState: Bundle?) {

       intent2 = Intent(this, Main2Activity::class.java)
        db= NoteDataBase.getDatabase(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = allNote(itemList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        val c = Calendar.getInstance()
        var year=c.get(Calendar.YEAR)
        var mois=c.get(Calendar.MONTH)
        var day=c.get(Calendar.DAY_OF_MONTH)
        val add=findViewById<Button>(R.id.addTacheBtnView)
          val listall=db.daoNote().fetchAll()
if(listall.isNotEmpty()){
     for (i in listall) {


         day = i.jour.toInt()
         mois = i.mois.toInt()
         year = i.annee.toInt()
         val titre = i.titre.toString()
         val description = i.desc.toString()
         val id = i.position
         if (id>=0){
         val note = Note(id, titre, day, mois, year, description)

         itemList.add(note)

         update()}

     }
     }

        add.setOnClickListener{
            val mDialog= LayoutInflater.from(this).inflate(R.layout.layout_dialog,null)
            val mBuilder =AlertDialog.Builder(this)
                .setView(mDialog)
                .setTitle("Ajouter note")
            val mAlert= mBuilder.show()
            val spinner: Spinner = mDialog.findViewById(R.id.combobox)
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
                    pst= position


                }

            }


           mDialog.ajtdate.setOnClickListener {

                val datePickerDialog = DatePickerDialog(this,R.style.AppTheme, DatePickerDialog.OnDateSetListener { test, mYear, mMonth, dayOfMonth ->
                 mDialog.date.setText(""+dayOfMonth+"_"+mMonth+"_"+mYear)
                    day=dayOfMonth
                    mois=mMonth
                    year=mYear

                }, year,mois,day)

                datePickerDialog.show()


            }
            mDialog.ajouter.setOnClickListener{
                mAlert.dismiss()
                val titre = mDialog.title.text.toString()
                val description=mDialog.description.text.toString()
                val note = Note(0,titre,day,mois,year,description)
              db.daoNote().insert(note)
              //  db1.daoNote().insert(note)
                itemList.add(note)
                update()

            }

        }
    }
    fun update(){

            list.clear()
            list =allNote(itemList)
            adapter.notifyDataSetChanged()

    }
    fun allNote(lists : MutableList<Note>):MutableList<Int>{
        var list : MutableList<Int> = ArrayList()
        for (i in 1 until lists.size){
            list.add(i)
        }
        return list
    }

}
