package com.example.tp4_exo3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.exo2_tdm2.MainActivity
import com.example.exo2_tdm2.Note
import com.example.exo2_tdm2.NoteDataBase
import com.example.exo2_tdm2.R

/**
 *Created by Fedala Amira.
 */
class RecyclerAdapter(val activity : MainActivity) : RecyclerView.Adapter<RecyclerAdapter.recyclerViewHolder>(){

    class recyclerViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val itemLayout = v.findViewById<RelativeLayout>(R.id.tacheLayoutView)
        val image=v.findViewById<ImageView>(R.id.delete)
        val titre = v.findViewById<TextView>(R.id.titre)
        val date=v.findViewById<TextView>(R.id.date)
        val desc=v.findViewById<TextView>(R.id.desc)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
        return recyclerViewHolder(LayoutInflater.from(activity).inflate(R.layout.note_view, parent, false))

    }

    override fun getItemCount(): Int {
        return activity.list.size
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {

          activity.position=position

        holder.titre.text = activity.itemList[activity.list[position]].titre
        holder.desc.text=activity.itemList[activity.list[position]].desc
        holder.date.text=activity.itemList[activity.list[position]].annee.toString()+"_"+activity.itemList[activity.list[position]].mois.toString()+"_"+activity.itemList[activity.list[position]].jour.toString()
         holder.itemLayout.setBackgroundColor(activity.color(activity.pst,holder.itemLayout))
        holder.image.setOnClickListener{

            activity.db.daoNote().delete(activity.itemList[activity.list[position]])
            activity.itemList.removeAt(activity.list[position])



            activity.update()
        }




    }
}