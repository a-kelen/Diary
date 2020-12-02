package com.diary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert
     fun insert(note: Note) : Long

    @Update
    fun update(note: Note)

    @Query("SELECT * from note_table WHERE noteId = :key")
    fun get(key: Long): Note?

    @Query("DELETE FROM note_table")
    fun clear()
    @Transaction
    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<NotesWithFolder>>
}
