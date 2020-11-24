package com.diary.notedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class NoteDetailsViewModel(elem: String) : ViewModel() {
    private val _elem : MutableLiveData<String> = MutableLiveData<String>()
    val element : LiveData<String>
        get() = _elem

    init {
        _elem.value = elem
    }
}
