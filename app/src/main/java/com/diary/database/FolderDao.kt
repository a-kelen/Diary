package com.diary.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FolderDao {
    @Insert
    fun insert(folder: Folder)

    @Update
    fun update(folder: Folder)

    @Query("SELECT * from folder_table WHERE folderId = :key")
    fun get(key: Long): Folder?

    @Query("DELETE FROM folder_table")
    fun clear()

    @Query("SELECT * FROM folder_table")
    fun getAllFolders(): LiveData<List<Folder>>
}
