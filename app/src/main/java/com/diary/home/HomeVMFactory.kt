package com.diary.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diary.database.DiaryDatabase
import com.diary.database.NoteDao
import java.lang.IllegalArgumentException

class HomeVMFactory(
    private val dataSource: DiaryDatabase,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java))
            return HomeViewModel(
                dataSource,
                application
            ) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
