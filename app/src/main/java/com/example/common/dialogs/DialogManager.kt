package com.example.common.dialogs

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogManager (
    val context: Context,
    val fragmentManager:FragmentManager
        ){

    fun showSomeInfo(tag:String,title:String, desc:String, btCta:String){
        InfoDialog.newInstance(title,desc,btCta).show(
            fragmentManager, tag)
    }

    fun showSomePrompt(tag:String,title:String, desc:String, btCta1:String, btCta2:String){
        PromptDialog.newInstance(title, desc, btCta1, btCta2).show(
            fragmentManager, tag)
    }

    fun getShownDialogTag(): String? {
        for (fragment in fragmentManager.getFragments()) {
            if (fragment is DialogFragment) {
                return fragment.getTag()
            }
        }
        return null
    }

}