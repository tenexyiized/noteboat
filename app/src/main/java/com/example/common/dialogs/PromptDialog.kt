package com.example.common.dialogs

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.common.eventbus.CustomEventBus
import com.example.notesboats.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PromptDialog : DialogFragment(R.layout.fragment_prompt_dialog) {

    @Inject
    lateinit var customEventBus: CustomEventBus

    lateinit var bt1 : Button
    lateinit var bt2 : Button
    lateinit var tvTitle : TextView
    lateinit var tvDesc : TextView

    companion object{
        fun newInstance(title:String, desc:String, btCta1:String, btCta2: String) : InfoDialog{

            return InfoDialog().apply {
                arguments = Bundle().apply {
                    putString("title",title)
                    putString("desc",desc)
                    putString("btCta1",btCta1)
                    putString("btCta2",btCta2)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt1 = view.findViewById<Button>(R.id.bt1)
        bt2 = view.findViewById<Button>(R.id.bt2)
        tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        tvTitle.text = arguments?.getString("title")
        tvDesc.text = arguments?.getString("desc")
        bt1.text = arguments?.getString("btCta1")
        bt2.text = arguments?.getString("btCta2")
        bt1.setOnClickListener {
            customEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
            dismiss()
        }
        bt2.setOnClickListener {
            customEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
            dismiss()
        }
    }

}