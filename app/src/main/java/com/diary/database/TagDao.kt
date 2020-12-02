package com.diary.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TagDao {
    @Insert
    fun insert(tag: Tag)

    @Update
    fun update(tag: Tag)

    @Query("SELECT * from tag_table WHERE tagId = :key")
    fun get(key: Long): Tag?

    @Query("DELETE FROM tag_table")
    fun clear()

    @Query("SELECT * FROM tag_table")
    fun getAllTags(): LiveData<List<Tag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTagsForNote(list: List<NoteTagCrossRef>)
}
