package com.example.notes.repository

import com.example.notes.data.model.Note
import com.example.notes.data.model.NoteList

interface NoteRepository {

    suspend fun getNotes():NoteList
    suspend fun saveNote(note: Note?):Note?
}