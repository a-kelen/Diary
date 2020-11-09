package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    var Id : Long = 0L,
    @ColumnInfo(name = "title")
    var Title : String,
    @ColumnInfo(name = "content")
    var Content : String?,
    @ColumnInfo(name = "created")
    val Created: Long = System.currentTimeMillis()
)
