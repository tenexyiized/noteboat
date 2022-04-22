package com.example.common.dialogs

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.common.eventbus.CustomEventBus
import com.example.notesboats.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PromptDialog : DialogFragment(R.layout.fragment_prompt_dialog) {

    @Inject
    lateinit var customEventBus:CustomEventBus

    companion object{
        fun newInstance():PromptDialog{
            return PromptDialog()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.bt1).setOnClickListener {
            customEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
        }

        view.findViewById<TextView>(R.id.bt2).setOnClickListener {
            customEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
        }
    }

}