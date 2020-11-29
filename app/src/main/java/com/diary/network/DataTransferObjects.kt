package com.diary.network

import com.diary.database.ArchiveNote
import com.diary.domain.Note
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkNoteContainer(val notes: List<NetworkNote>)

@JsonClass(generateAdapter = true)
data class NetworkNote(
    val noteId : Long,
    val title : String?,
    val content : String?,
    val created: Long ,
    val photo : String?,
    val emotion : Int = -1)

fun NetworkNoteContainer.asDatabaseModel(): Array<ArchiveNote> {
    return notes.map {
        ArchiveNote (
            noteId = it.noteId,
            title = it.title,
            content = it.content,
            created = it.created,
            photo = it.photo,
            emotion = it.emotion)
    }.toTypedArray()
}

fun NetworkNoteContainer.asDomainModel(): List<Note> {
    return notes.map {
        Note(
            noteId = it.noteId,
            title = it.title,
            content = it.content,
            created = it.created,
            photo = it.photo,
            emotion = it.emotion)
    }
}
