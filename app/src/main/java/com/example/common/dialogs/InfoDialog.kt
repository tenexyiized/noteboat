package com.example.common.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    lateinit var bt1 : Button
    lateinit var tvTitle : TextView
    lateinit var tvDesc : TextView

    companion object{
        fun newInstance(title:String, desc:String, btCta:String) : InfoDialog{

            return InfoDialog().apply {
                arguments = Bundle().apply {
                    putString("title",title)
                    putString("desc",desc)
                    putString("btCta",btCta)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt1 = view.findViewById<Button>(R.id.bt1)
        tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        tvTitle.text = arguments?.getString("title")
        tvDesc.text = arguments?.getString("desc")
        bt1.text = arguments?.getString("btCta")
        bt1.setOnClickListener {
            customEventBus.postEvent(InfoDialogEvent())
            dismiss()
        }
    }

}