package com.diary.database

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "tagId"])
data class NoteTagCrossRef (
    val noteId : Long,
    val tagId : Long
)
