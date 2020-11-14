package com.diary

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diary.database.NoteDao
import java.lang.IllegalArgumentException

class CreateNoteVMFactory(
    private val dataSource: NoteDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateNoteViewModel::class.java))
            return CreateNoteViewModel(dataSource, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
