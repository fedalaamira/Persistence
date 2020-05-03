package com.example.exo2_tdm2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable

/**
 *Created by Fedala Amira.
 *
 */
@Database(entities= [Note::class],version=1)
abstract class NoteDataBase:RoomDatabase() {
    abstract fun daoNote():DaoNote
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.

        private var INSTANCE: NoteDataBase? = null

        fun getDatabase(context: Context): NoteDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                   NoteDataBase::class.java,
                    "notelast2"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance as NoteDataBase
            }
        }}

        }