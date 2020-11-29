package com.diary.archive

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.diary.database.NoteArchiveDao
import com.diary.database.NoteDao
import com.diary.domain.Note
import com.diary.network.DiaryApi
import com.diary.network.asDatabaseModel
import com.diary.network.asDomainModel
import com.diary.repository.ArchiveNoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ArchiveViewModel(
    val db : NoteArchiveDao,
    application: Application
) : AndroidViewModel(application)  {

    private val repository  = ArchiveNoteRepository(db)
    val notes = repository.archiveNotes

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

//    private var viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        viewModelScope.launch {
            repository.refreshArchiveNotes()
        }
    }

}
