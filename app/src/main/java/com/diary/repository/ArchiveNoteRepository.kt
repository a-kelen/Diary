package com.diary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.diary.database.DiaryDatabase
import com.diary.database.NoteArchiveDao
import com.diary.database.asDomainModel
import com.diary.domain.Note
import com.diary.network.DiaryApi
import com.diary.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArchiveNoteRepository(private val archiveDao: NoteArchiveDao) {

    val archiveNotes: LiveData<List<Note>> = Transformations.map(archiveDao.getNotes()) {
        it.asDomainModel()
    }

    suspend fun refreshArchiveNotes() {
        withContext(Dispatchers.IO){
            val list = DiaryApi.retrofitService.getList()
            archiveDao.insertAll(*list.asDatabaseModel())
        }
    }
}
