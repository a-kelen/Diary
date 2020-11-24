package com.diary.notedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class NoteDetailsViewModelFactory(private val elem: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteDetailsViewModel::class.java))
            return NoteDetailsViewModel(elem) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
