package com.southernsunrise.notesappbottomnav.fragments.starreds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southernsunrise.notesappbottomnav.data.repository.INoteRepository
import com.southernsunrise.notesappbottomnav.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class StarredViewModel(private val noteRepository: INoteRepository) : ViewModel() {

    val starredNotesFlow: Flow<List<NoteModel>> = noteRepository.subscribeToAllStarredNotes()
        .map { noteEntities -> noteEntities.map { noteEntity -> noteEntity.toNoteModel() } }

    fun removeNoteFromStarred(note: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote((note.apply { isStarred = false }).toNoteEntity())
        }
    }
}