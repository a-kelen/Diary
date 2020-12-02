package com.diary.domain

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.diary.database.Tag

data class Note(
    val noteId : Long,
    val title : String?,
    val content : String?,
    val created: Long ,
    val photo : String?,
    val emotion : Int = -1,
    val folderName : String?,
    val tags: List<Tag> = ArrayList(),
    val tagsCount: String = "0"
)
