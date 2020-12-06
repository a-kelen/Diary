package com.diary.notedetails

import android.app.Application
import android.app.TimePickerDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.database.*
import com.diary.domain.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

public class NoteDetailsViewModel(private val elemId: Long,
                                  val db : DiaryDatabase,
                                  application: Application
) : ViewModel() {
    private val _elem : MutableLiveData<Note> = MutableLiveData<Note>()
    val element : LiveData<Note>
        get() = _elem

    init {
        viewModelScope.launch {
            _elem.value = getNote(elemId)
            Timber.i(_elem.value.toString())
        }

    }
    private suspend fun getNote(id: Long): Note? {

        return withContext(Dispatchers.IO) {
            var note = db.noteDao.get(id)?.asDomainModel()
            return@withContext note
        }
    }
}
