package com.example.exo2_tdm2

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *Created by Fedala Amira.
 */
@Entity (
    tableName="notelast2"
)
data class Note (
    @PrimaryKey (autoGenerate = true) var position:Int,
    var titre:String,
    var jour:Int,
    var mois:Int,
    var annee:Int,

    var desc:String

)