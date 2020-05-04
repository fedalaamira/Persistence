package com.example.tp4_exo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.exo6_tdm2.Main2Activity
import com.example.exo6_tdm2.MainActivity
import com.example.exo6_tdm2.R

/**
 *Created by Fedala Amira.
 */
class RecyclerAdapter(val activity : Main2Activity) : RecyclerView.Adapter<RecyclerAdapter.recyclerViewHolder>(){

    class recyclerViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val itemLayout = v.findViewById<RelativeLayout>(R.id.tacheLayoutView)

        val module= v.findViewById<TextView>(R.id.module)
        val salle=v.findViewById<TextView>(R.id.enseignant)
        val enseignant=v.findViewById<TextView>(R.id.salle)
        val date=v.findViewById<TextView>(R.id.date)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        return recyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.seance_view, parent, false))

    }

    override fun getItemCount(): Int {
        return activity.list.size
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {

        activity.position=position

        holder.module.text = activity.itemList[activity.list[position]].module
        holder.salle.text=activity.itemList[activity.list[position]].salle
        holder.date.text=activity.itemList[activity.list[position]].annee.toString()+"_"+activity.itemList[activity.list[position]].mois.toString()+"_"+activity.itemList[activity.list[position]].jour.toString()
        holder.enseignant.text=activity.itemList[activity.list[position]].prof



    }
}