package com.example.notes.data.model

fun List<NoteEntity>.toNoteList(): NoteList{
    val list = mutableListOf<Note>()

    this.forEach{ noteEntity ->
        list.add(noteEntity.toNote())
    }
    return NoteList(list)
}

fun NoteEntity.toNote():Note= Note(
    this.id.toString(),
    this.title,
    this.content,
    this.image
)

fun Note.toNoteEntity():NoteEntity= NoteEntity(
    this.id.toInt(),
    this.title,
    this.content,
    this.image
)