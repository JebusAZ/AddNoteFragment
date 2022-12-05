package com.example.notes.repository

import com.example.notes.data.local.LocalDataSource
import com.example.notes.data.model.Note
import com.example.notes.data.model.NoteList
import com.example.notes.data.model.toNoteEntity
import com.example.notes.data.remote.NoteDataSource

class NoteRepositoryImp(

    private val localDataSource: LocalDataSource,
    private val dataSource: NoteDataSource): NoteRepository {
    override suspend fun getNotes(): NoteList {

        dataSource.getNotes().data.forEach { note ->
            localDataSource.saveNote(note.toNoteEntity())
        }

        return localDataSource.getNotes()
    }

    override suspend fun saveNote(note: Note?): Note? {
        return dataSource.saveNotes(note)
    }
}