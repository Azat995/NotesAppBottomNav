package com.southernsunrise.notesappbottomnav.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.southernsunrise.notesappbottomnav.data.dao.NoteDao
import com.southernsunrise.notesappbottomnav.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}