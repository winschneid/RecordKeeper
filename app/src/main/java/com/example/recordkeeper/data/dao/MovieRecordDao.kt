package com.example.recordkeeper.data.dao

import androidx.room.*
import com.example.recordkeeper.data.entity.MovieRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieRecordDao {
    @Query("SELECT * FROM movie_records ORDER BY date DESC")
    fun getAllMovieRecords(): Flow<List<MovieRecord>>

    @Insert
    suspend fun insertMovieRecord(movieRecord: MovieRecord)

    @Update
    suspend fun updateMovieRecord(movieRecord: MovieRecord)

    @Delete
    suspend fun deleteMovieRecord(movieRecord: MovieRecord)

    @Query("SELECT * FROM movie_records WHERE id = :id")
    suspend fun getMovieRecordById(id: Long): MovieRecord?

    @Query("SELECT COUNT(*) FROM movie_records WHERE theater = :theater")
    suspend fun getTheaterVisitCount(theater: String): Int

    @Query("SELECT DISTINCT theater FROM movie_records WHERE theater LIKE '%' || :query || '%' ORDER BY theater")
    suspend fun getTheaterSuggestions(query: String): List<String>
}