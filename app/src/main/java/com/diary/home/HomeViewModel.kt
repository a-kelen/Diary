package com.diary.home

import android.app.Application
import androidx.lifecycle.*
import com.diary.database.NoteDao
import com.diary.database.asDomainModel
import com.diary.database.asDomainModel2
import com.diary.domain.Note
import kotlinx.coroutines.*
import timber.log.Timber

class HomeViewModel(
    val db : NoteDao,
    application: Application
) : AndroidViewModel(application) {
    val notes = Transformations.map(db.getAllNotes()) {
        Timber.i("Notes count: " + it.count().toString())
        it.asDomainModel2()
    }
    private val _event = MutableLiveData<Boolean>()
    val  event : LiveData<Boolean>
        get() = _event

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    lateinit var initList : ArrayList<Note>

    init {
        initList = ArrayList()
    }

    val clearButtonVisible = Transformations.map(notes) {
        it?.isNotEmpty()
    }

    fun addElement() {
        var n = com.diary.database.Note(title = "Title 1", content = "Content")
        viewModelScope.launch {
            insertNote(n)
        }
        this._event.value = true
    }

    private suspend fun insertNote(n: com.diary.database.Note) {
//        return withContext(Dispatchers.IO) {
//            db.insert(n)
//        }
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
