package com.southernsunrise.notesappbottomnav.fragments.notes.createNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.southernsunrise.notesappbottomnav.R
import com.southernsunrise.notesappbottomnav.activities.NotesMainActivity
import com.southernsunrise.notesappbottomnav.databinding.FragmentCreateNoteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateNoteFragment : Fragment() {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    private val createNoteViewModel by viewModel<CreateNoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setOnBackPressedCallback()
    }

    private fun setupViews() {
        setupEditTexts()
        setClickListeners()
    }

    private fun setupEditTexts() {
        binding.apply {
            noteTitleEditText.setOnTextChangeListener()
            noteContentEditText.setOnTextChangeListener()
        }
    }

    private fun EditText.setOnTextChangeListener() {
        this.addTextChangedListener {
            it?.let {
                binding.doneButton.isEnabled = editTextsInputValid()
            }
        }
    }

    private fun editTextsInputValid() =
        binding.noteTitleEditText.text.toString()
            .isNotBlank() && binding.noteContentEditText.text.toString().isNotBlank()


    private fun setOnBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setClickListeners() = with(binding) {
        doneButton.setOnClickListener {
            val colorList = resources.getStringArray(R.array.notes_cards_background_color_hex_codes)
            createNoteViewModel.addNewNote(noteTitleEditText.text.toString().trim(), noteContentEditText.text.toString().trim(), colorList.random())
            requireActivity().supportFragmentManager.popBackStack()
        }
        backButton.setOnClickListener {
            if (noteTitleEditText.text.isNotEmpty() || noteContentEditText.text.isNotEmpty()) showNoteCreateDiscardDialog()
            else requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun showNoteCreateDiscardDialog() {
        val alertDialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Are you sure you want to discard?")
            .setMessage("data you have input will be lost.")
            .setPositiveButton("Yes") { dialog, _ -> requireActivity().supportFragmentManager.popBackStack() }
            .setNegativeButton("Cancel", /* listener = */ null)
        alertDialog.show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // setting the dispatcher to the NotesMainActivity default
        (requireActivity() as NotesMainActivity).setOnBackPressedDispatcher()
    }
}