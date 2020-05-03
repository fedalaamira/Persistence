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
    @Query("SELECT * FROM seances WHERE position LIKE :position")
    fun findByPosition(position:Int):Seance




}