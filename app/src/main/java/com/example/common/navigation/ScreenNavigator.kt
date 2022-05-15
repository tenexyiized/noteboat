package com.example.common.navigation

interface ScreenNavigator {

    fun openBlankFragment()

    fun openBlankFragment2()

    fun openBlankFragment3()

    fun clearBackStackAndOpenBlankFragment4()

    fun openBlankFragment5()

    fun handleOnBackPressedInFragment():Boolean

    fun goBackFromFragment5ToFragment2()

    fun openNotesList()

    fun openAddNotesPage(id:Long?)

}