package com.diary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Note::class, Tag::class, Folder::class], version = 2, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao
    companion object {
        @Volatile
        private var INSTANCE : DiaryDatabase? = null
        fun getInstance(context: Context) : DiaryDatabase {
            synchronized(this) {
                var inst = INSTANCE
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = inst
                }
                return inst
            }
        }
    }
}
