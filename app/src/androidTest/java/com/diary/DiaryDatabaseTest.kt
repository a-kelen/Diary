package com.diary

import android.util.Log
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.diary.database.DiaryDatabase
import com.diary.database.Note
import com.diary.database.NoteDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DiaryDatabaseTest {
    private lateinit var noteDao: NoteDao
    private lateinit var db: DiaryDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, DiaryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        noteDao = db.noteDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNote() {
        var note = Note(Title = "new", Content = "Content")
        noteDao.insert(note)
        val res = noteDao.get(1)
        assertEquals("new", res?.Title)
        assertEquals(res?.Content, "Content")
    }
}

