package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diary.domain.Note

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val noteId : Long = 0L,
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "content")
    val content : String?,
    @ColumnInfo(name = "created")
    val created: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "photo")
    val photo : String? = null,
    @ColumnInfo(name = "emotion")
    val emotion : Int = -1,
    val folderId : Long = 0L
)
fun List<com.diary.database.Note>.asDomainModel(): List<Note> {
    return map {
        Note(
            noteId = it.noteId,
            title = it.title,
            content = it.content,
            created = it.created,
            photo = it.photo,
            emotion = it.emotion,
            folderName = "Default")
    }
}

fun List<com.diary.database.NotesWithFolder>.asDomainModel2(): List<Note> {
    return map {
        Note(
            noteId = it.note.noteId,
            title = it.note.title,
            content = it.note.content,
            created = it.note.created,
            photo = it.note.photo,
            emotion = it.note.emotion,
            folderName = it.folder?.name,
            tags = it.tags,
            tagsCount = it.tags.size.toString() + " tag(s)"
        )
    }
}


