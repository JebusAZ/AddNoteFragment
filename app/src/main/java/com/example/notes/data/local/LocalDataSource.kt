package com.example.notes.data.local

import com.example.notes.data.model.NoteEntity
import com.example.notes.data.model.NoteList
import com.example.notes.data.model.toNoteList

class LocalDataSource(private val noteDao: NoteDao) {

    suspend fun getNotes():NoteList = noteDao.getNotes().toNoteList()

    suspend fun saveNote(note:NoteEntity) = noteDao.saveNote(note)

}