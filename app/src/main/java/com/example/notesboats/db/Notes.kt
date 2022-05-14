package com.example.notesboats.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="notes")
data class Notes (
    var title: String,
    var description:String,
    var lastUpdated:Long,
    @PrimaryKey(autoGenerate = true)
    var id:Long? = null
        )