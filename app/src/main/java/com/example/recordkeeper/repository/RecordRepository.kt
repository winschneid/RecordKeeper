package com.example.recordkeeper.repository

import com.example.recordkeeper.data.dao.LiveRecordDao
import com.example.recordkeeper.data.dao.MovieRecordDao
import com.example.recordkeeper.data.dao.RamenRecordDao
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.MovieRecord
import com.example.recordkeeper.data.entity.RamenRecord
import kotlinx.coroutines.flow.Flow

class RecordRepository(
    private val liveRecordDao: LiveRecordDao,
    private val movieRecordDao: MovieRecordDao,
    private val ramenRecordDao: RamenRecordDao
) {
    fun getAllLiveRecords(): Flow<List<LiveRecord>> = liveRecordDao.getAllLiveRecords()
    fun getAllMovieRecords(): Flow<List<MovieRecord>> = movieRecordDao.getAllMovieRecords()
    fun getAllRamenRecords(): Flow<List<RamenRecord>> = ramenRecordDao.getAllRamenRecords()

    suspend fun insertLiveRecord(liveRecord: LiveRecord) {
        liveRecordDao.insertLiveRecord(liveRecord)
    }

    suspend fun insertMovieRecord(movieRecord: MovieRecord) {
        movieRecordDao.insertMovieRecord(movieRecord)
    }

    suspend fun insertRamenRecord(ramenRecord: RamenRecord) {
        ramenRecordDao.insertRamenRecord(ramenRecord)
    }

    suspend fun updateLiveRecord(liveRecord: LiveRecord) {
        liveRecordDao.updateLiveRecord(liveRecord)
    }

    suspend fun updateMovieRecord(movieRecord: MovieRecord) {
        movieRecordDao.updateMovieRecord(movieRecord)
    }

    suspend fun updateRamenRecord(ramenRecord: RamenRecord) {
        ramenRecordDao.updateRamenRecord(ramenRecord)
    }

    suspend fun deleteLiveRecord(liveRecord: LiveRecord) {
        liveRecordDao.deleteLiveRecord(liveRecord)
    }

    suspend fun deleteMovieRecord(movieRecord: MovieRecord) {
        movieRecordDao.deleteMovieRecord(movieRecord)
    }

    suspend fun deleteRamenRecord(ramenRecord: RamenRecord) {
        ramenRecordDao.deleteRamenRecord(ramenRecord)
    }
}