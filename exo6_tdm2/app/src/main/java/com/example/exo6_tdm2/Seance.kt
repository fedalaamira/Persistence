package com.example.exo6_tdm2

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.sql.Time
import java.util.*

/**
 *Created by Fedala Amira.
 */

@Entity(
    tableName="seances2"
)
data class Seance (
    @PrimaryKey(autoGenerate = true) var position:Int,
    var module:String,
    var prof:String,
    var salle:String,
    var jour:Int,
    var mois:Int,
    var annee:Int



)