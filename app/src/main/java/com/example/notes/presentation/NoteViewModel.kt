package com.example.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.notes.core.Resource
import kotlinx.coroutines.Dispatchers
import com.example.notes.data.model.Note
import com.example.notes.repository.NoteRepository
import java.lang.Exception


class NoteViewModel(private val repository: NoteRepository) :ViewModel() {

    //Fix
    fun fetchNotes() = liveData(Dispatchers.IO){

        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getNotes()))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }


    fun saveNotes(note: Note?) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.saveNote(note)))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }
}


class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass:Class<T>):T{
        return modelClass.getConstructor(NoteRepository::class.java).newInstance(repository)
    }
}
