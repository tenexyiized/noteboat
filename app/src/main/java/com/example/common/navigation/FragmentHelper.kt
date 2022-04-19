package com.example.common.navigation

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentHelper (
    val activity:Activity,
    val fragmentContainerWrapper: FragmentContainerWrapper,
    val fragmentManager: FragmentManager
        ){

    fun replaceFragment(fragment: Fragment, tag:String?=null, bundle: Bundle= Bundle(), backStackName:String?=null, clearHistory: Boolean = false, addToBackStack: Boolean = true){

        if (clearHistory) {
            // Remove all entries from back stack
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        val ft: FragmentTransaction = fragmentManager.beginTransaction()
        if(addToBackStack) ft.addToBackStack(backStackName)
        // Change to a new fragment
        fragment.arguments = bundle
        ft.replace(getFragmentFrameId(), fragment)
        ft.commit()
    }

    //FragmentManager.POP_BACK_STACK_INCLUSIVE

    fun popBackTo(backStackName: String? = null, inclusive:Int = 0){
        Log.d("cnrs","pop " + backStackName + inclusive + fragmentManager.backStackEntryCount)
        var i = 0
        while (i <  fragmentManager.backStackEntryCount){

            Log.d("cnrs","pop " + fragmentManager.getBackStackEntryAt(i).name)

            fragmentManager.getBackStackEntryAt(i).name
            i++
        }
        fragmentManager.popBackStack(backStackName, inclusive)
    }

    fun handleOnBackPressedInFragment():Boolean = goBackInFragmentsHistory()

    private fun goBackInFragmentsHistory(): Boolean {
        if (getFragmentsHistoryCount() > 0) {
            // A call to popBackStack can leave the currently visible fragment on screen. Therefore,
            // we start with manual removal of the current fragment.
            // Description of the issue can be found here: https://stackoverflow.com/q/45278497/2463035
            removeCurrentFragment()
            fragmentManager.popBackStackImmediate()
            return true
        }
        return false
    }

    private fun removeCurrentFragment() {
        var fm = fragmentManager
        getCurrentFragment()?.apply {
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.remove(this)
            ft.commit()
        }

        // not sure it is needed; will keep it as a reminder to myself if there will be problems
        // mFragmentManager.executePendingTransactions();
    }

    private fun getCurrentFragment(): Fragment? {
        return fragmentManager.findFragmentById(getFragmentFrameId())
    }

    private fun getFragmentsHistoryCount(): Int {
        // TODO: double check that fragments history count equals to backstack entry count
        return fragmentManager.getBackStackEntryCount()
    }


    private fun getFragmentFrameId(): Int  = fragmentContainerWrapper.getFragmentContainer().getId()


}