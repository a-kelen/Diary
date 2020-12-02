package com.diary.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class NotesWithFolder (
    @Embedded
    val note: Note,
    @Relation(
        parentColumn = "folderId",
        entityColumn = "folderId"
    )
    val folder: Folder,
    @Relation(
    parentColumn = "noteId",
    entityColumn = "tagId",
    associateBy = Junction(NoteTagCrossRef::class)
    )
    val tags: List<Tag>
)
