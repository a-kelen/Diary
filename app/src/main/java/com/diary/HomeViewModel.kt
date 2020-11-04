package com.diary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class HomeViewModel : ViewModel() {
    private val _notes : MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()
    val notes: LiveData<ArrayList<String>>
        get() = _notes

    private val _event = MutableLiveData<Boolean>()
    val  event : LiveData<Boolean>
        get() = _event

    lateinit var initList : ArrayList<String>
    init {
        initList = ArrayList()
        initialize()
        _notes.value = initList
    }
    fun initialize() {
        for (i in 0..4) {
            initList.add("Element $i")
            _notes.value = initList
        }
    }
    fun addElement() {
        initList.add("New element " + (_notes.value?.size))
        _notes.value = initList
        this._event.value = true
    }
    fun reloadEvent() {
        this._event.value = false
    }
}
