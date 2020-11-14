package com.diary.database

import androidx.room.Embedded
import androidx.room.Relation

data class NotesWithFolder (
    @Embedded
    val note: Note,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "folderId"
    )
    val folder: Folder
)
