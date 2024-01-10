@file:Suppress("RemoveExplicitTypeArguments")

package com.southernsunrise.notesappbottomnav.di

import com.southernsunrise.notesappbottomnav.data.repository.INoteRepository
import com.southernsunrise.notesappbottomnav.fragments.notes.createNote.CreateNoteViewModel
import com.southernsunrise.notesappbottomnav.fragments.notes.noteDetails.NoteDetailsViewModel
import com.southernsunrise.notesappbottomnav.fragments.notes.notes.NotesViewModel
import com.southernsunrise.notesappbottomnav.fragments.starreds.StarredViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { NotesViewModel(noteRepository = get<INoteRepository>()) }
    viewModel { NoteDetailsViewModel(noteRepository = get<INoteRepository>()) }
    viewModel { CreateNoteViewModel(noteRepository = get<INoteRepository>()) }
    viewModel { StarredViewModel(noteRepository = get<INoteRepository>()) }

}
