package com.example.common.viewstates

import com.example.common.helpers.OneShotEvent
import com.example.notesboats.db.Notes

data class NoteAddViewState (
    var note:OneShotEvent<Notes>? = null,
    var id:Long? = null,
    var isSaved:OneShotEvent<Boolean>? = null
    )
