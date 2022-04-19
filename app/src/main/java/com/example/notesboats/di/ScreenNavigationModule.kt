package com.example.notesboats.di

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.example.common.dialogs.DialogManager
import com.example.common.navigation.MyFragmentFactory
import com.example.common.navigation.FragmentContainerWrapper
import com.example.common.navigation.FragmentHelper
import com.example.common.navigation.ScreenNavigator
import com.example.common.navigation.ScreenNavigationImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ScreenNavigationModule {

    @ActivityScoped
    @Provides
    fun screenNavigator(
        helper: FragmentHelper,
        activity: Activity,
        fragmentFactory: FragmentFactory
    ): ScreenNavigator = ScreenNavigationImpl(
        helper,
        activity,
        fragmentFactory
    )

    @ActivityScoped
    @Provides
    fun fragmentHelper(
        activity:Activity,
        fragmentContainerWrapper: FragmentContainerWrapper,
        fragmentManager: FragmentManager
    ): FragmentHelper = FragmentHelper(
        activity,
        fragmentContainerWrapper,
        fragmentManager
    )

    @ActivityScoped
    @Provides
    fun fragmentContainerWrapper(
        activity:Activity
    ): FragmentContainerWrapper = activity as FragmentContainerWrapper

    @ActivityScoped
    @Provides
    fun fragmentManager(activity: Activity)
            : FragmentManager = (activity as AppCompatActivity).supportFragmentManager

    @ActivityScoped
    @Provides
    fun getFragmentFactory():FragmentFactory = MyFragmentFactory()

    @ActivityScoped
    @Provides
    fun getDialogManager(
        @ActivityContext context: Context,
        fragmentManager: FragmentManager
    ): DialogManager = DialogManager(
        context,
        fragmentManager
    )

}