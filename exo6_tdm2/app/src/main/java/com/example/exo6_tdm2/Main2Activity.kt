package com.example.exo6_tdm2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exo2_tdm2.SeanceDataBase
import com.example.tp4_exo3.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    var pst: Int = 0
    var position:Int=0
    var positionL:Int=1
    lateinit var layoutManager : LinearLayoutManager
    var itemList : MutableList<Seance> = ArrayList()
    lateinit var list : MutableList<Int>
    lateinit var adapter: RecyclerAdapter
    lateinit var db: SeanceDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        list = allSeance(itemList)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
   lateinit var listall:List<Seance>
        adapter = RecyclerAdapter(this)
        db= SeanceDataBase.getDatabase(this)
        recyclerView.adapter = adapter
        if(intent.getIntExtra("pst",0)==1){

            listall=db.daoSeance().findBymodule(intent.getStringExtra("text"))

        }
        else if(intent.getIntExtra("pst",0)==2){
            listall=db.daoSeance().findByprof(intent.getStringExtra("text"))

        }else if(intent.getIntExtra("pst",0)==3){
          listall=db.daoSeance().findBysalle(intent.getStringExtra("text"))
        }else if (intent.getIntExtra("pst",0)==5) {

            listall=db.daoSeance().fetchAll()

        }else{
            listall=db.daoSeance().findByDate(intent.getIntExtra("mois",0),intent.getIntExtra("year",0),intent.getIntExtra("day",0))

        }

        if(listall.isNotEmpty()){
            for (i in listall) {

                val day = i.jour.toInt()
                val mois = i.mois.toInt()
                val year = i.annee.toInt()
                val module = i.module.toString()
                val prof = i.prof.toString()
                val salle= i.salle.toString()
                val week =intent.getIntExtra("nbweek",0)
                val jr=intent.getIntExtra("day",0)
                val yr=intent.getIntExtra("year",0)
                val mnth=intent.getIntExtra("mois",0)
                val id = i.position
                if (intent.getIntExtra("pst",0)==5){
                    val jr=intent.getIntExtra("dayweek",0)
                    val yr=intent.getIntExtra("yearweek",0)
                    val mnth=intent.getIntExtra("moisweek",0)
                    if (i.jour>week+1 && i.jour< 31-7+week){
                        if (year==yr && mois== mnth+1 && day>day-week && day < day+7-week) {
                            val seance = Seance(id, module, prof,salle,day,mois,year)

                            itemList.add(seance)

                            update()
                        }

                    }
                    else if (i.jour<week+1){
                        if (year==yr && (mois==mnth|| mois==mnth+1) && day > jr-week+31 && day < jr+7-week){
                            val seance = Seance(id, module, prof,salle,day,mois,year)

                            itemList.add(seance)

                            update()
                        }
                    }
                    else if ( i.jour> 31-7+week){
                        if (year==yr && (mois == mnth+2|| mois==mnth+1 ) && day >jr-week && day < jr+7-week-31 ){
                            val seance = Seance(id, module, prof,salle,day,mois,year)

                            itemList.add(seance)

                            update()
                        }
                    }

                }
                else {
                    val seance = Seance(id, module, prof,salle,day,mois,year)

                    itemList.add(seance)

                    update()}

            }
        }

    }
    fun update(){

        list.clear()
        list =allSeance(itemList)
        adapter.notifyDataSetChanged()

    }
    fun allSeance(lists : MutableList<Seance>):MutableList<Int>{
        var list : MutableList<Int> = ArrayList()
        for (i in 1 until lists.size){
            list.add(i)
        }
        return list
    }
}
