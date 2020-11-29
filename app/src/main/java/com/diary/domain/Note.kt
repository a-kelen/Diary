package com.diary.domain

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Note(
    val noteId : Long,
    val title : String?,
    val content : String?,
    val created: Long ,
    val photo : String?,
    val emotion : Int = -1
)
