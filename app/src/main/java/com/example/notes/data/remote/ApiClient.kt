package com.example.notes.data.remote

import com.google.gson.GsonBuilder
import com.example.notes.app.constants

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient{
    val service by lazy{
        Retrofit.Builder()
            .baseUrl(constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)
    }
}
