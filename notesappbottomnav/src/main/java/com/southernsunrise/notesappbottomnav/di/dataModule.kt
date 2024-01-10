@file:Suppress("RemoveExplicitTypeArguments")

package com.southernsunrise.notesappbottomnav.di

import androidx.room.Room
import com.southernsunrise.notesappbottomnav.application.NotesApplication
import com.southernsunrise.notesappbottomnav.data.dao.NoteDao
import com.southernsunrise.notesappbottomnav.data.database.AppDatabase
import com.southernsunrise.notesappbottomnav.data.repository.INoteRepository
import com.southernsunrise.notesappbottomnav.data.repository.NoteRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    /** AppDatabase */
    single<AppDatabase> {
        Room.databaseBuilder(NotesApplication.applicationContext(), AppDatabase::class.java, "notes_database").build()
    }

    /** Dao */
    single<NoteDao> { get<AppDatabase>().noteDao() }

    /** Repository */
    single<INoteRepository> {
        NoteRepositoryImpl(get<NoteDao>())
    }
}
