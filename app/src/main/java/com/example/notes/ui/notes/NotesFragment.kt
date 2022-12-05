package com.example.notes.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notes.R
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notes.core.Resource
import com.example.notes.data.local.AppDatabase
import com.example.notes.data.local.LocalDataSource
import com.example.notes.data.remote.ApiClient
import com.example.notes.data.remote.NoteDataSource
import com.example.notes.databinding.FragmentNotesBinding
import com.example.notes.presentation.NoteViewModel
import com.example.notes.presentation.NoteViewModelFactory
import com.example.notes.repository.NoteRepositoryImp
import com.example.notes.ui.notes.adapters.NoteAdapter


class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NoteAdapter

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

        binding = FragmentNotesBinding.bind(view)

        binding.recyclerNotes.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.fetchNotes().observe(viewLifecycleOwner, Observer { result ->
            when (result) {

                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    adapter =
                        NoteAdapter(result.data.data) { note ->                    // onNoteClick(note)
                        }
                    binding.recyclerNotes.adapter = adapter
                    // Log.d("LiveData","${result.data.toString()}")
                }
                is Resource.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    // Log.d("LiveData","${result.exception.toString()}")
                }
            }
        })
        binding.btnAddNote.setOnClickListener{
            findNavController().navigate(R.id.action_notesFragment_to_noteEditFragment)
        }

    }

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

}