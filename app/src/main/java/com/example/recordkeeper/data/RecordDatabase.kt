package com.example.recordkeeper.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import android.content.Context
import com.example.recordkeeper.data.dao.LiveRecordDao
import com.example.recordkeeper.data.dao.MovieRecordDao
import com.example.recordkeeper.data.dao.RamenRecordDao
import com.example.recordkeeper.data.entity.LiveRecord
import com.example.recordkeeper.data.entity.MovieRecord
import com.example.recordkeeper.data.entity.RamenRecord

@Database(
    entities = [LiveRecord::class, MovieRecord::class, RamenRecord::class],
    version = 3,
    exportSchema = false
)
abstract class RecordDatabase : RoomDatabase() {
    abstract fun liveRecordDao(): LiveRecordDao
    abstract fun movieRecordDao(): MovieRecordDao
    abstract fun ramenRecordDao(): RamenRecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // locationカラムを削除
                database.execSQL("CREATE TABLE ramen_records_new (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, shopName TEXT NOT NULL, menuName TEXT NOT NULL, date TEXT NOT NULL, rating INTEGER NOT NULL, memo TEXT NOT NULL)")
                database.execSQL("INSERT INTO ramen_records_new (id, shopName, menuName, date, rating, memo) SELECT id, shopName, menuName, date, rating, memo FROM ramen_records")
                database.execSQL("DROP TABLE ramen_records")
                database.execSQL("ALTER TABLE ramen_records_new RENAME TO ramen_records")
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // imagePathカラムを追加
                database.execSQL("ALTER TABLE ramen_records ADD COLUMN imagePath TEXT")
            }
        }

        fun getDatabase(context: Context): RecordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordDatabase::class.java,
                    "record_database"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build()
                INSTANCE = instance
                instance
            }
        }
    }
}