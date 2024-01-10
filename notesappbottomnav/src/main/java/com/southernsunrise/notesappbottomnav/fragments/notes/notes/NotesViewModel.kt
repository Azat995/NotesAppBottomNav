package com.southernsunrise.notesappbottomnav.fragments.notes.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helperlibrary.showToast
import com.southernsunrise.notesappbottomnav.application.NotesApplication
import com.southernsunrise.notesappbottomnav.data.entity.NoteEntity
import com.southernsunrise.notesappbottomnav.data.repository.INoteRepository
import com.southernsunrise.notesappbottomnav.models.NoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NotesViewModel(private val noteRepository: INoteRepository) : ViewModel() {

    var notesFlow: Flow<List<NoteModel>> = noteRepository.subscribeToAllNotes()
        .map { noteEntities -> noteEntities.map { noteEntity -> noteEntity.toNoteModel() } }

    fun updateNoteStarredState(note: NoteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insertNote((note.apply { isStarred = !isStarred }).toNoteEntity())
        }
    }

    fun addNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            NotesApplication.applicationContext().showToast("Start adding notes")
            val currentTimeInMillis = System.currentTimeMillis()
            repeat(30) {
                delay((0L..600L).random())
                val newNoteEntity = NoteEntity(
                    title = "title $it",
                    contentText = "noteContent $it",
                    createdDateInMillis = currentTimeInMillis,
                    updatedDateInMillis = currentTimeInMillis,
                    isStarred = false,
                    cardBackgroundColorHex = "#FD99FF"
                )
                noteRepository.insertNote(newNoteEntity)
            }
        }
    }

    fun removeNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            // _notesDataChangeState.postValue(RecyclerViewChangeState.Removed(position))
            noteRepository.deleteNote(id)
        }
    }

}
