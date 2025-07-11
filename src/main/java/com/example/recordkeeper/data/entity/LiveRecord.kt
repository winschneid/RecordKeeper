package com.example.recordkeeper.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "live_records")
data class LiveRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val artist: String,
    val venue: String,
    val date: String,
    val rating: Int,
    val memo: String = ""
)