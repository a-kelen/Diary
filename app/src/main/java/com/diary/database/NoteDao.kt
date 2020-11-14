package com.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Query("SELECT * from note_table WHERE noteId = :key")
    fun get(key: Long): Note?

    @Query("DELETE FROM note_table")
    fun clear()

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<Note>>
}
