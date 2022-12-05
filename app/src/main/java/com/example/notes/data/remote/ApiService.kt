package com.example.notes.data.remote

import com.example.notes.data.model.Note
import com.example.notes.data.model.NoteList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface ApiService {
    @GET("note")

    suspend fun getNotes(): NoteList

    @POST("note")
    suspend fun saveNote(@Body note: Note?) : Note?




}