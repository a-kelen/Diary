package com.diary.archive

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.diary.database.Note
import com.diary.database.NoteDao
import com.diary.network.DiaryApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class ArchiveViewModel(
    val db : NoteDao,
    application: Application
) : AndroidViewModel(application)  {
    private var _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>>
        get() = _notes

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init {
        getList()
    }

    private fun getList() {
        viewModelScope.launch {
            try {
                var listResult = DiaryApi.retrofitService.getList()
                _status.value = "Done"
                _notes.value = listResult
            } catch (e: Exception) {
                _status.value = e.toString()
            }
        }
    }
}
