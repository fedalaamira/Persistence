package com.example.exo6_tdm2

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Created by Fedala Amira.
 */

@Entity(
    tableName="seances"
)
data class Seance (
    @PrimaryKey(autoGenerate = true) var position:Int,
    var prof:String,
    var salle:String


)