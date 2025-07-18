package com.example.recordkeeper.data.dao

import androidx.room.*
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.ArtistSuggestion
import com.example.recordkeeper.data.entity.VenueSuggestion
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

    @Query("SELECT COUNT(*) FROM live_records WHERE artist = :artist")
    suspend fun getArtistLiveCount(artist: String): Int

    @Query("SELECT artist, COUNT(*) as count, MAX(date) as latest_date FROM live_records WHERE artist LIKE '%' || :query || '%' GROUP BY artist ORDER BY count DESC, latest_date DESC")
    suspend fun getArtistSuggestionsWithFrequency(query: String): List<ArtistSuggestion>

    @Query("SELECT venue, COUNT(*) as count, MAX(date) as latest_date FROM live_records WHERE venue LIKE '%' || :query || '%' GROUP BY venue ORDER BY count DESC, latest_date DESC")
    suspend fun getVenueSuggestionsWithFrequency(query: String): List<VenueSuggestion>

    @Query("SELECT DISTINCT artist FROM live_records WHERE artist LIKE '%' || :query || '%' ORDER BY artist")
    suspend fun getArtistSuggestions(query: String): List<String>

    @Query("SELECT DISTINCT venue FROM live_records WHERE venue LIKE '%' || :query || '%' ORDER BY venue")
    suspend fun getVenueSuggestions(query: String): List<String>
}