package com.example.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.notesboats.R

class InfoDialog : DialogFragment(R.layout.fragment_info_dialog) {

    companion object{
        fun newInstance() : InfoDialog{
            return InfoDialog()
        }
    }

}