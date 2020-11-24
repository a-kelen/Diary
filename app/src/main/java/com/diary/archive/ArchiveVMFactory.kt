package com.diary.archive

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diary.database.NoteDao
import java.lang.IllegalArgumentException


class ArchiveVMFactory(
    private val dataSource: NoteDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArchiveViewModel::class.java))
            return ArchiveViewModel(dataSource, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
