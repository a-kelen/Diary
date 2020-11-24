package com.diary.createnote

import android.app.Application
import androidx.lifecycle.*
import com.diary.database.Note
import com.diary.database.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateNoteViewModel(val db : NoteDao, application: Application) : AndroidViewModel(application) {

    private val _event = MutableLiveData<Boolean>()
    val  NoteCretedEvent : LiveData<Boolean>
        get() = _event

    fun createNote() {
        var n = Note(title = "Title 1", content = "Content")
        viewModelScope.launch {
            insertNote(n)
        }
        this._event.value = true
    }
    private suspend fun insertNote(n: Note) {
        return withContext(Dispatchers.IO) {
            db.insert(n)

        }
    }
    fun reloadNoteCreatedEvent() {
        this._event.value = false
    }
}
