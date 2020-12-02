package com.diary.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diary.notedetails.NoteDetailsViewModel
import java.lang.IllegalArgumentException

class TabCreateFViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TabCreateFViewModel::class.java))
            return TabCreateFViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
