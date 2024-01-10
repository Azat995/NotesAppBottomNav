package com.southernsunrise.notesappbottomnav.recyclerviews.notesRecyclerview

import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.southernsunrise.notesappbottomnav.databinding.NotesCardRecyclerViewItemBinding
import com.southernsunrise.notesappbottomnav.models.NoteModel

class NotesRecyclerViewAdapter(
    val onNoteTappedCallback: (note: NoteModel) -> Unit,
    private val onNoteStarredStateChangeCallback: (noteModel: NoteModel) -> Unit
) :
    ListAdapter<NoteModel, NotesViewHolder>(NotesDiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
            onNoteTappedCallback,
            onNoteStarredStateChangeCallback,
            NotesCardRecyclerViewItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}