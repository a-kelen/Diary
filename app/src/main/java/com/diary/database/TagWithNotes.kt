package com.diary.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TagWithNotes (
    @Embedded
    val tag: Tag,
    @Relation(
        parentColumn = "tagId",
        entityColumn = "noteId",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val notes: List<Note>
)
