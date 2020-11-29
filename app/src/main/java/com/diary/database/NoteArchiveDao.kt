package com.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteArchiveDao {
    @Query("select * from archive_note_table")
    fun getNotes(): LiveData<List<ArchiveNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg notes: ArchiveNote)
}
