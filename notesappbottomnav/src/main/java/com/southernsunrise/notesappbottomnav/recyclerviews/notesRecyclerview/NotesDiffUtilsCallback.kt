package com.southernsunrise.notesappbottomnav.recyclerviews.notesRecyclerview

import androidx.recyclerview.widget.DiffUtil
import com.southernsunrise.notesappbottomnav.models.NoteModel

class NotesDiffUtilsCallback : DiffUtil.ItemCallback<NoteModel>() {

    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }


}