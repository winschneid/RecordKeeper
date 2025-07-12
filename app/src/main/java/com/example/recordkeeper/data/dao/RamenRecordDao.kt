package com.example.recordkeeper.data.dao

import androidx.room.*
import com.example.recordkeeper.data.entity.RamenRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface RamenRecordDao {
    @Query("SELECT * FROM ramen_records ORDER BY date DESC")
    fun getAllRamenRecords(): Flow<List<RamenRecord>>

    @Insert
    suspend fun insertRamenRecord(ramenRecord: RamenRecord)

    @Update
    suspend fun updateRamenRecord(ramenRecord: RamenRecord)

    @Delete
    suspend fun deleteRamenRecord(ramenRecord: RamenRecord)

    @Query("SELECT * FROM ramen_records WHERE id = :id")
    suspend fun getRamenRecordById(id: Long): RamenRecord?

    @Query("SELECT COUNT(*) FROM ramen_records WHERE shopName = :shopName")
    suspend fun getShopVisitCount(shopName: String): Int

    @Query("SELECT DISTINCT shopName FROM ramen_records WHERE shopName LIKE '%' || :query || '%' ORDER BY shopName")
    suspend fun getShopNameSuggestions(query: String): List<String>

    @Query("SELECT DISTINCT menuName FROM ramen_records WHERE menuName LIKE '%' || :query || '%' ORDER BY menuName")
    suspend fun getMenuNameSuggestions(query: String): List<String>
}