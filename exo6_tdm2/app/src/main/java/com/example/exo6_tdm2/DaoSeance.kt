package com.example.exo2_tdm2

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.exo6_tdm2.Seance

/**
 *Created by Fedala Amira.
 */
@Dao
interface DaoSeance {

    @Insert
    fun insertAll(vararg seances: Seance)
    @Update
    fun update(seances:Seance)
    @Delete
    fun delete(seances:Seance)
    @Query("SELECT * FROM seances2 WHERE module LIKE :module")
    fun findBymodule(module:String):List<Seance>
    @Query("SELECT * FROM seances2 WHERE prof LIKE :prof")
    fun findByprof(prof:String):List<Seance>
    @Query("SELECT * FROM seances2 WHERE salle LIKE :salle")
    fun findBysalle(salle:String):List<Seance>
    @Query("SELECT * FROM seances2")
    fun fetchAll():List<Seance>
    @Query("SELECT * FROM seances2 where   mois =:mois+1 AND annee=:annee AND jour=:jour ")
    fun findByDate(mois:Int,annee:Int,jour:Int):List<Seance>



}