package com.southernsunrise.notesappbottomnav.recyclerviews.helpers

import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.southernsunrise.notesappbottomnav.models.NoteModel
import com.southernsunrise.notesappbottomnav.recyclerviews.notesRecyclerview.NotesViewHolder
import java.util.ArrayList
import java.util.Collections


fun RecyclerView.attachVerticalSwapItemTouchHelper(
    onSwapCallback: (sourcePosition: Int, targetPosition: Int) -> Unit

) = ItemTouchHelper(object :
    ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
    override fun onMove(
        recyclerView: RecyclerView,
        source: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val sourcePosition = source.adapterPosition
        val targetPosition = target.adapterPosition
        onSwapCallback(sourcePosition, targetPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("Not yet implemented")
    }

}).also { it.attachToRecyclerView(this) }

fun RecyclerView.attachLeftSwipeRemoveItemTouchHelper(
    onItemRemovedCallBack: (position: Int) -> Unit
) = ItemTouchHelper(object :
    ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT
    ) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
        //Remove swiped item from list and notify the RecyclerView
        val position = viewHolder.adapterPosition
        onItemRemovedCallBack(position)
    }
}).also { it.attachToRecyclerView(this) }

