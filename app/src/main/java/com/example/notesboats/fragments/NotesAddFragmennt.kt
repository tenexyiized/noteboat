package com.example.notesboats.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.common.helpers.launchAndRepeatWithViewLifecycle
import com.example.notesboats.R
import com.example.notesboats.db.Notes
import com.example.notesboats.viewmodels.NotesAddViewModel
import com.example.notesboats.viewmodels.NotesListViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotesAddFragmennt : Fragment(R.layout.fragment_notes_add){

    val viewModel: NotesAddViewModel by viewModels()

    private lateinit var btn: Button
    private lateinit var description_til: TextInputLayout
    private lateinit var title_til: TextInputLayout


    companion object{
        val TAG = "NotesAddFragmennt"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn = view.findViewById(R.id.save_btn)
        description_til = view.findViewById(R.id.description_til)
        title_til = view.findViewById(R.id.title_til)

        val id = arguments?.getLong("id")

        id?.let{
            viewModel.setId(it)
            viewModel.getNotes(it)
        }

        btn.setOnClickListener {
            viewModel.addNotes(
                Notes(title = title_til.editText?.text.toString(),
                      description = description_til.editText?.text.toString(),
                    lastUpdated = System.currentTimeMillis()
                    )
            )
        }

        launchAndRepeatWithViewLifecycle {
            launch {
                viewModel.uiState.collect{
                    it.note?.getContentIfNotHandled()?.let{
                        description_til.editText?.setText(it.description)
                        title_til.editText?.setText(it.title)
                    }
                }
            }
        }

    }

}