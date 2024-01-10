package com.southernsunrise.notesappbottomnav.fragments.notes.notes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.southernsunrise.notesappbottomnav.databinding.FragmentNotesBinding
import com.southernsunrise.notesappbottomnav.models.NoteModel
import com.southernsunrise.notesappbottomnav.recyclerviews.helpers.attachLeftSwipeRemoveItemTouchHelper
import com.southernsunrise.notesappbottomnav.recyclerviews.helpers.attachVerticalSwapItemTouchHelper
import com.southernsunrise.notesappbottomnav.recyclerviews.notesRecyclerview.NotesRecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel by viewModel<NotesViewModel>()
    private var notesAdapter: NotesRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers() = with(notesViewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            notesFlow.collectLatest { notes ->
                binding.viewNoNotes.isVisible = notes.isEmpty()
                updateRecyclerView(notes)
            }
        }
        addNotes()
    }

    private fun setupViews() {
        setupNotesRecyclerView()
    }

    private fun setupNotesRecyclerView() {
        notesAdapter =
            NotesRecyclerViewAdapter(
                onNoteTappedCallback = ::onNoteTapCallback,
                onNoteStarredStateChangeCallback = ::updateNoteStarredState
            )
        binding.notesRecyclerView.apply {
            adapter = notesAdapter
            attachVerticalSwapItemTouchHelper(::onSwapCallBack)
            attachLeftSwipeRemoveItemTouchHelper(::removeNoteCallback)
        }

    }

    private fun onNoteTapCallback(note: NoteModel) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesFragmentToNoteDetailsFragment(
                note
            )
        )
    }

    private fun onSwapCallBack(sourcePosition: Int, targetPosition: Int) {
//        Collections.swap(notesList, sourcePosition, targetPosition)
        notesAdapter?.notifyItemMoved(sourcePosition, targetPosition)

    }

    private fun removeNoteCallback(position: Int) {
//        notesViewModel.removeNote(notesList[position].id)
        notesAdapter?.notifyItemRemoved(position)

    }

    private fun updateNoteStarredState(note: NoteModel) {
        notesViewModel.updateNoteStarredState(note = note)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateRecyclerView(list: List<NoteModel>) {
        notesAdapter?.apply {
            submitList(list)
            notifyDataSetChanged()
            currentList.takeIf { it.isNotEmpty() }?.lastIndex?.let { binding.notesRecyclerView.smoothScrollToPosition(it) }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        notesAdapter = null
    }
}