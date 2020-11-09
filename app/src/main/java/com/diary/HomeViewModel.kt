package com.diary

import android.app.Application
import android.opengl.Visibility
import android.util.Log
import android.widget.Button
import androidx.lifecycle.*
import com.diary.database.Note
import com.diary.database.NoteDao
import kotlinx.coroutines.*

class HomeViewModel(
    val db : NoteDao,
    application: Application
) : AndroidViewModel(application) {
    private var _notes = db.getAllNotes()
    val notes: LiveData<List<Note>>
        get() = _notes

    private val _event = MutableLiveData<Boolean>()
    val  event : LiveData<Boolean>
        get() = _event

    lateinit var initList : ArrayList<Note>
    init {
        initList = ArrayList()
    }

    val clearButtonVisible = Transformations.map(_notes) {
        it?.isNotEmpty()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun addElement() {
        var n = Note(Title = "Title 1", Content = "Content")
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
    fun clearNotes() {
        viewModelScope.launch {
            clear()
        }
        _showSnackbarEvent.value = true
    }
    private suspend fun clear()
    {
        return withContext(Dispatchers.IO) {
            db.clear()
        }
    }
    fun reloadEvent() {
        this._event.value = false
    }

    override fun onCleared() {
        super.onCleared()
    }
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }
}
