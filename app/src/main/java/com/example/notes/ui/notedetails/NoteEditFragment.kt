package com.example.notes.ui.notedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.notes.R
import com.example.notes.core.Resource
import com.example.notes.data.local.AppDatabase
import com.example.notes.data.local.LocalDataSource
import com.example.notes.data.model.Note
import com.example.notes.data.remote.ApiClient
import com.example.notes.data.remote.NoteDataSource
import com.example.notes.presentation.NoteViewModel
import com.example.notes.presentation.NoteViewModelFactory
import com.example.notes.repository.NoteRepositoryImp
import com.example.notes.databinding.FragmentNoteEditBinding

class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {

    lateinit var binding: FragmentNoteEditBinding

    private val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(
            NoteRepositoryImp(
                LocalDataSource(AppDatabase.getDatabase(this.requireContext()).noteDao()),
                NoteDataSource(ApiClient.service)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteEditBinding.bind(view)

        binding.btnAddNote.setOnClickListener { view ->
            val titleNote: String = binding.editTitle.text.toString()
            val commentForNote: String = binding.editContent.text.toString()
            val imageProfile: String = binding.editImageUrl.text.toString()
            val addNote = Note(
                "", titleNote,
                commentForNote,
                imageProfile
            )

            viewModel.saveNotes(addNote).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Toast.makeText(context, "Nota guardada", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Failure -> {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}