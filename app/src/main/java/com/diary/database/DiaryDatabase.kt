package com.diary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Note::class, Tag::class, Folder::class, ArchiveNote::class, NoteTagCrossRef::class], version = 9, exportSchema = false)
abstract class DiaryDatabase : RoomDatabase() {
    abstract val noteDao : NoteDao
    abstract val archiveDao : NoteArchiveDao
    abstract val folderDao : FolderDao
    abstract val tagDao: TagDao
    companion object {
        @Volatile
        private lateinit var INSTANCE : DiaryDatabase
        fun getInstance(context: Context) : DiaryDatabase {
            synchronized(DiaryDatabase::class.java) {

                if (! ::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DiaryDatabase::class.java,
                        "diary_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return INSTANCE
            }
        }
    }
}
