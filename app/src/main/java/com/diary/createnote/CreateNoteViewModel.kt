package com.diary.createnote

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import com.diary.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.ArrayList

class CreateNoteViewModel(val db : DiaryDatabase, application: Application) : AndroidViewModel(application) {
    private val _event = MutableLiveData<Boolean>()
    val  NoteCretedEvent : LiveData<Boolean>
        get() = _event
    private val _event_created_folder = MutableLiveData<Boolean>()
    val  FolderCretedEvent : LiveData<Boolean>
        get() = _event_created_folder
    private val _event_created_tag = MutableLiveData<Boolean>()
    val  TagCretedEvent : LiveData<Boolean>
        get() = _event_created_tag
    //change
    val title = MutableLiveData<String>()
    val content = MutableLiveData<String>()

    val folderName = MutableLiveData<String>()
    val folders = db.folderDao.getAllFolders()

    val tagName = MutableLiveData<String>()
    val tags = db.tagDao.getAllTags()

    val imageUri = MutableLiveData<Uri>()

    val emotion = MutableLiveData<Int>()

    val selectedFolder = MutableLiveData<Long>()

    val arr = MutableLiveData<ArrayList<Long>>()

    fun createNote() {
        if(
            emotion.value != null &&
            selectedFolder.value != null
        ) {
            var note = Note(
                title = this.title.value,
                content = this.content.value,
                emotion = this.emotion.value !! -1,
                folderId = this.selectedFolder.value !! + (1).toLong() !! -1,
                photo = imageUri.value.toString()
            )
            viewModelScope.launch {
                insertNote(note)
            }
            this._event.value = true
        }
    }
    private suspend fun insertNote(n: Note) {
        return withContext(Dispatchers.IO) {
            var newId = db.noteDao.insert(n)

            val list = ArrayList<NoteTagCrossRef>()
            for (a in arr.value!!) {
                list.add(NoteTagCrossRef(tagId = a, noteId = newId))
            }
            db.tagDao.insertTagsForNote(list)
            arr.value!!.clear()
        }
    }
    fun reloadNoteCreatedEvent() {
        this._event.value = false
    }

    fun createFolder() {
        var folder = Folder(name = this.folderName.value.toString())
        viewModelScope.launch {
            insertFolder(folder)
        }
        this.folderName.value = ""
        this._event_created_folder.value = true
    }
    private suspend fun insertFolder(f: Folder) {
        return withContext(Dispatchers.IO) {
            db.folderDao.insert(f)
        }
    }
    fun reloadFolderCreatedEvent() {
        this._event_created_folder.value = false
    }

    fun createTag() {
        var tag = Tag(name = this.tagName.value.toString())
        viewModelScope.launch {
            insertTag(tag)
        }
        this.tagName.value = ""
        this._event_created_tag.value = true
    }
    private suspend fun insertTag(t: Tag) {
        return withContext(Dispatchers.IO) {
            db.tagDao.insert(t)
        }
    }
    fun reloadTagCreatedEvent() {
        this._event_created_tag.value = false
    }
}
