package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_table")
data class Tag (
    @PrimaryKey(autoGenerate = true)
    var tagId : Long = 0L,
    @ColumnInfo(name = "name")
    var name : String
)
