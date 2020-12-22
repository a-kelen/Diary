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
    val folder: Folder?,
    @Relation(
    parentColumn = "noteId",
    entityColumn = "tagId",
    associateBy = Junction(NoteTagCrossRef::class)
    )
    val tags: List<Tag>
)

fun NotesWithFolder.asDomainModel(): com.diary.domain.Note {
    return com.diary.domain.Note(
        noteId = this.note.noteId,
        title = this.note.title,
        created = this.note.created,
        content = this.note.content,
        tagsCount = this.tags.size.toString(),
        tags = this.tags,
        photo = this.note.photo,
        folderName = this.folder?.name,
        emotion = this.note.emotion
    )
}
