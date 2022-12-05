package com.example.notes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.data.model.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun noteDao():NoteDao
    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            INSTANCE =Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "noteapp2022"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}