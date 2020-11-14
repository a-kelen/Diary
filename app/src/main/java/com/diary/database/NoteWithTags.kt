package com.diary.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class NoteWithTags (
    @Embedded
    val note: Note,
    @Relation(
        parentColumn = "noteId",
        entityColumn = "tagId",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val tags: List<Tag>
)
