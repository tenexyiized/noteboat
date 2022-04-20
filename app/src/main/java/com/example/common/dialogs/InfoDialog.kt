package com.example.common.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.common.eventbus.CustomEventBus
import com.example.notesboats.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InfoDialog : DialogFragment(R.layout.fragment_info_dialog) {

    @Inject
    lateinit var customEventBus: CustomEventBus

    companion object{
        fun newInstance() : InfoDialog{
            return InfoDialog()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tvInfo).setOnClickListener {
            customEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
        }
        Log.d("cnre",customEventBus.toString())
    }

}