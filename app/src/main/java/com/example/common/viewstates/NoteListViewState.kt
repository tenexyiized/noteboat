package com.example.common.viewstates

import com.example.notesboats.db.Notes

data class NoteListViewState(
    var notesList:List<Notes> = mutableListOf()
)