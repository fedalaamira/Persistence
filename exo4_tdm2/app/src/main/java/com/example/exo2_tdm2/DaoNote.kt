package com.example.exo2_tdm2

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery

/**
 *Created by Fedala Amira.
 */
@Dao
interface DaoNote {

   @Insert
   fun insert(note:Note)
    @Update
    fun update(note:Note)
   @Delete
    fun delete(note:Note)
    @Query("SELECT * FROM notelast2 WHERE position LIKE :position")
    fun findByPosition(position:Int):Note
   /* @RawQuery
    fun getNoteViaQuery(query:SupportSQLiteQuery)*/
    @Query("SELECT * FROM notelast2")
    fun fetchAll():List<Note>


}