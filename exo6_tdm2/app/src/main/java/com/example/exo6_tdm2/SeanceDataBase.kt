package com.example.exo2_tdm2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomMasterTable
import com.example.exo6_tdm2.Seance

/**
 *Created by Fedala Amira.
 *
 */
@Database(entities= [Seance::class],version=1)
abstract class SeanceDataBase:RoomDatabase() {
    abstract fun daoSeance():DaoSeance
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.

        private var INSTANCE: SeanceDataBase? = null

        fun getDatabase(context: Context): SeanceDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SeanceDataBase::class.java,
                    "seances2"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance as SeanceDataBase
            }
        }}

}