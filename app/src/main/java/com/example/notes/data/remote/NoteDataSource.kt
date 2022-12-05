package com.example.notes.data.remote

import com.example.notes.data.model.Note
import com.example.notes.data.model.NoteList

class NoteDataSource(private val apiService: ApiService){

    suspend fun getNotes():NoteList = apiService.getNotes()

    suspend fun saveNotes(note: Note?):Note?{
        apiService.saveNote(note)
        return note
    }
}