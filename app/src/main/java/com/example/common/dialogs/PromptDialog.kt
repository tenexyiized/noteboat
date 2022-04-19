package com.example.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.notesboats.R

class PromptDialog : DialogFragment(R.layout.fragment_prompt_dialog) {

    companion object{
        fun newInstance():PromptDialog{
            return PromptDialog()
        }
    }

}