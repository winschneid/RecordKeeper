package com.example.recordkeeper.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    
    fun formatDate(date: LocalDate): String {
        return date.format(formatter)
    }
    
    fun parseDate(dateString: String): LocalDate? {
        return try {
            LocalDate.parse(dateString, formatter)
        } catch (e: Exception) {
            null
        }
    }
    
    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }
}