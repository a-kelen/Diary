package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val noteId : Long = 0L,
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "content")
    val content : String?,
    @ColumnInfo(name = "created")
    val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "photo")
    val photo : String? = null,
    @ColumnInfo(name = "emotion")
    val name : Int = -1,
    val folderId : Long = 0L
)
