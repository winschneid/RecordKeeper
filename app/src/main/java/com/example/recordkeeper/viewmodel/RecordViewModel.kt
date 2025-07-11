package com.example.recordkeeper.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recordkeeper.data.RecordDatabase
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.MovieRecord
import com.example.recordkeeper.data.entity.RamenRecord
import com.example.recordkeeper.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: RecordRepository
    val liveRecords: Flow<List<LiveRecord>>
    val movieRecords: Flow<List<MovieRecord>>
    val ramenRecords: Flow<List<RamenRecord>>

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    init {
        val database = RecordDatabase.getDatabase(application)
        repository = RecordRepository(
            database.liveRecordDao(),
            database.movieRecordDao(),
            database.ramenRecordDao()
        )
        liveRecords = repository.getAllLiveRecords()
        movieRecords = repository.getAllMovieRecords()
        ramenRecords = repository.getAllRamenRecords()
    }

    fun setSelectedTab(index: Int) {
        _selectedTab.value = index
    }

    fun insertLiveRecord(liveRecord: LiveRecord) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.insertLiveRecord(liveRecord)
            _isLoading.value = false
        }
    }

    fun insertMovieRecord(movieRecord: MovieRecord) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.insertMovieRecord(movieRecord)
            _isLoading.value = false
        }
    }

    fun insertRamenRecord(ramenRecord: RamenRecord) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.insertRamenRecord(ramenRecord)
            _isLoading.value = false
        }
    }

    fun deleteLiveRecord(liveRecord: LiveRecord) {
        viewModelScope.launch {
            repository.deleteLiveRecord(liveRecord)
        }
    }

    fun deleteMovieRecord(movieRecord: MovieRecord) {
        viewModelScope.launch {
            repository.deleteMovieRecord(movieRecord)
        }
    }

    fun deleteRamenRecord(ramenRecord: RamenRecord) {
        viewModelScope.launch {
            repository.deleteRamenRecord(ramenRecord)
        }
    }
}