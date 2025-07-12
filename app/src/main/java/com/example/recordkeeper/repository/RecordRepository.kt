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

    suspend fun getArtistLiveCount(artist: String): Int {
        return liveRecordDao.getArtistLiveCount(artist)
    }

    suspend fun getTheaterVisitCount(theater: String): Int {
        return movieRecordDao.getTheaterVisitCount(theater)
    }

    suspend fun getShopVisitCount(shopName: String): Int {
        return ramenRecordDao.getShopVisitCount(shopName)
    }

    suspend fun getArtistSuggestions(query: String): List<String> {
        return liveRecordDao.getArtistSuggestionsWithFrequency(query).map { it.artist }
    }

    suspend fun getVenueSuggestions(query: String): List<String> {
        return liveRecordDao.getVenueSuggestionsWithFrequency(query).map { it.venue }
    }

    suspend fun getTheaterSuggestions(query: String): List<String> {
        return movieRecordDao.getTheaterSuggestionsWithFrequency(query).map { it.theater }
    }

    suspend fun getShopNameSuggestions(query: String): List<String> {
        return ramenRecordDao.getShopNameSuggestionsWithFrequency(query).map { it.shopName }
    }

    suspend fun getMenuNameSuggestions(query: String): List<String> {
        return ramenRecordDao.getMenuNameSuggestionsWithFrequency(query).map { it.menuName }
    }
}