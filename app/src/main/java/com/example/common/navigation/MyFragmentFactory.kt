package com.example.common.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.notesboats.test_fragments.BlankFragment
import javax.inject.Inject


class MyFragmentFactory constructor() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            BlankFragment::class.java -> BlankFragment()
            else -> super.instantiate(classLoader, className)
        }
}