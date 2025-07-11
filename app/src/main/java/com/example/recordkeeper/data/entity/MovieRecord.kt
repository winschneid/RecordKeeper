package com.example.recordkeeper.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_records")
data class MovieRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val director: String,
    val theater: String,
    val date: String,
    val rating: Int,
    val memo: String = ""
)