package com.example.recordkeeper.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.recordkeeper.data.dao.LiveRecordDao
import com.example.recordkeeper.data.dao.MovieRecordDao
import com.example.recordkeeper.data.dao.RamenRecordDao
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.MovieRecord
import com.example.recordkeeper.data.entity.RamenRecord

@Database(
    entities = [LiveRecord::class, MovieRecord::class, RamenRecord::class],
    version = 1,
    exportSchema = false
)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun liveRecordDao(): LiveRecordDao
    abstract fun movieRecordDao(): MovieRecordDao
    abstract fun ramenRecordDao(): RamenRecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordDatabase::class.java,
                    "record_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}