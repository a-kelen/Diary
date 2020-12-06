package com.diary.home

import android.app.Application
import androidx.lifecycle.*
import com.diary.database.DiaryDatabase
import com.diary.database.NoteDao
import com.diary.database.asDomainModel
import com.diary.database.asDomainModel2
import com.diary.domain.Note
import kotlinx.coroutines.*
import timber.log.Timber

class HomeViewModel(
    val db : DiaryDatabase,
    application: Application
) : AndroidViewModel(application) {
    val filter = FilterHolder()
//    private val notes = MutableLiveData<List<Note>>()
    val notes = Transformations.map(db.noteDao.getAllNotes()) {
        it.asDomainModel2()
    }
    val folderlist = db.folderDao.getAllFolders()


    private val _event = MutableLiveData<Boolean>()
    val  event : LiveData<Boolean>
        get() = _event

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    lateinit var initList : ArrayList<Note>

    private val _navigateToNote = MutableLiveData<Long>()
    val navigateToNote
        get() = _navigateToNote

    init {
        initList = ArrayList()


    }



    fun onNoteClicked(id: Long){
        _navigateToNote.value = id
    }

    fun onNoteNavigated() {
        _navigateToNote.value = null
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
            db.noteDao.clear()
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

    fun onFilterChanged(filter: String, isChecked: Boolean) {
        if (this.filter.update(filter, isChecked)) {
            onQueryChanged()
        }
    }
    private fun onQueryChanged() {
        viewModelScope.launch {
//            notes.value =  db.noteDao.getAllNotes().value?.filter { x -> x.note.folderId == filter.currentValue }?.asDomainModel2()
        }
    }

     class FilterHolder {
        var currentValue: String? = null
            private set

        fun update(changedFilter: String, isChecked: Boolean): Boolean {
            if (isChecked) {
                currentValue = changedFilter
                return true
            } else if (currentValue == changedFilter) {
                currentValue = null
                return true
            }
            return false
        }
    }
}
