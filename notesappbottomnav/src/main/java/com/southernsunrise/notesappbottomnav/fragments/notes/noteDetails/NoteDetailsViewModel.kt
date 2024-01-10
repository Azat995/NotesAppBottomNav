package com.southernsunrise.notesappbottomnav.fragments.notes.noteDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.southernsunrise.notesappbottomnav.data.repository.INoteRepository
import com.southernsunrise.notesappbottomnav.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteDetailsViewModel(private val noteRepository: INoteRepository) : ViewModel() {

    fun saveNoteChanges(note: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote(note.toNoteEntity())
        }
    }

}
