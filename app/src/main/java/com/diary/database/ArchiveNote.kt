package com.diary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diary.domain.Note

@Entity(tableName = "archive_note_table")
data class ArchiveNote (
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
fun List<ArchiveNote>.asDomainModel(): List<Note> {
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
