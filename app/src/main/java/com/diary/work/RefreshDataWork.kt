package com.diary.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.diary.database.DiaryDatabase
import com.diary.repository.ArchiveNoteRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params)
{
    companion object {
        const val WORK_NAME = "RefreshDataWork"
    }

    override suspend fun doWork(): Result {
        val db = DiaryDatabase.getInstance(applicationContext)
        val repository = ArchiveNoteRepository(db.archiveDao)

        return try {
            repository.refreshArchiveNotes()
            Result.success()
        }catch (ex: HttpException) {
            Result.retry()
        }
    }

}
