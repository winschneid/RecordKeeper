package com.example.recordkeeper.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ramen_records")
data class RamenRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shopName: String,
    val menuName: String,
    val date: String,
    val rating: Int,
    val memo: String = "",
    val imagePath: String? = null
)