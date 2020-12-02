package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "folder_table"
    )
data class Folder (
    @PrimaryKey(autoGenerate = true)
    var folderId : Long = 0L,
    @ColumnInfo(name = "name")
    var name : String
)
