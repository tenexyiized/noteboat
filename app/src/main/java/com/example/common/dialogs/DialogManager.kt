package com.example.common.dialogs

import android.content.Context
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogManager (
    val context: Context,
    val fragmentManager:FragmentManager
        ){

    fun showSomeInfo(tag:String){
        InfoDialog.newInstance().show(
            fragmentManager, tag)
    }

    fun showSomePrompt(tag:String){
        PromptDialog.newInstance().show(
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