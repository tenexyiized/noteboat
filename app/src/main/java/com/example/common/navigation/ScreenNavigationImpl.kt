package com.example.common.navigation

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.example.notesboats.fragments.NotesAddFragmennt
import com.example.notesboats.fragments.NotesListFragment
import com.example.notesboats.test_fragments.*

class ScreenNavigationImpl (
    val helper: FragmentHelper,
    val activity: Activity,
    val fragmentFactory: FragmentFactory
) : ScreenNavigator {

    override fun openBlankFragment(){
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            BlankFragment::class.java.getName()
        )
        val bundle = bundleOf("some_int" to 0)
        helper.replaceFragment(fragment,bundle = bundle, addToBackStack = false)
    }

    override fun openBlankFragment2() {
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            BlankFragment2::class.java.getName()
        )
        val bundle = bundleOf("some_int" to 0)
        helper.replaceFragment(fragment,bundle = bundle, backStackName = BlankFragment2.sag)
    }

     override fun openBlankFragment3() {
         val fragment: Fragment = fragmentFactory.instantiate(
             activity.getClassLoader(),
             BlankFragment3::class.java.getName()
         )
         val bundle = bundleOf("some_int" to 0)
         helper.replaceFragment(fragment, bundle = bundle, backStackName = BlankFragment3.sag)
    }

    override fun clearBackStackAndOpenBlankFragment4() {
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            BlankFragment4::class.java.getName()
        )
        val bundle = bundleOf("some_int" to 0)
        helper.replaceFragment(fragment,bundle = bundle, backStackName = BlankFragment4.sag, clearHistory = true)
    }

    override fun openBlankFragment5() {
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            BlankFragment5::class.java.getName()
        )
        val bundle = bundleOf("some_int" to 0)
        helper.replaceFragment(fragment,bundle = bundle, backStackName = BlankFragment5.sag)
    }

    override fun handleOnBackPressedInFragment() = helper.handleOnBackPressedInFragment()

    override fun goBackFromFragment5ToFragment2() {
        helper.popBackTo("BlankFragment2", FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun openNotesList() {
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            NotesListFragment::class.java.getName()
        )
        helper.replaceFragment(fragment, addToBackStack = false)
    }


    override fun openAddNotesPage(id: Long?) {
        val fragment: Fragment = fragmentFactory.instantiate(
            activity.getClassLoader(),
            NotesAddFragmennt::class.java.getName()
        )
        val bundle = bundleOf()
        id?.let{
             bundle.putLong("id",id)
        }
        helper.replaceFragment(fragment,bundle = bundle, backStackName = NotesAddFragmennt.TAG)
    }
}