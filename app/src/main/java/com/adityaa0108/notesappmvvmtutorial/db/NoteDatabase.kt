package com.adityaa0108.notesappmvvmtutorial.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adityaa0108.notesappmvvmtutorial.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false

)

abstract  class NoteDatabase:RoomDatabase() {

    abstract fun getNoteDao():DAO
    companion object{
        @Volatile
        private var instance:NoteDatabase?= null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also {
                instance = it
            }
        }
        private fun createDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_database").build()


    }


}