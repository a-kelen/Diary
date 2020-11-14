package com.diary.database

import androidx.room.Embedded
import androidx.room.Relation

data class FoldersWithNotes (
    @Embedded
    val folder : Folder,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "folderId"
    )
    val notes: List<Note>
)
