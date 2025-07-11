package com.example.recordkeeper.data.dao

import androidx.room.*
import com.example.recordkeeper.data.entity.LiveRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface LiveRecordDao {
    @Query("SELECT * FROM live_records ORDER BY date DESC")
    fun getAllLiveRecords(): Flow<List<LiveRecord>>

    @Insert
    suspend fun insertLiveRecord(liveRecord: LiveRecord)

    @Update
    suspend fun updateLiveRecord(liveRecord: LiveRecord)

    @Delete
    suspend fun deleteLiveRecord(liveRecord: LiveRecord)

    @Query("SELECT * FROM live_records WHERE id = :id")
    suspend fun getLiveRecordById(id: Long): LiveRecord?
}