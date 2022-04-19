package com.example.notesboats

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import android.content.pm.PackageManager
import android.util.Log
import android.view.ViewGroup
import com.example.common.dialogs.DialogManager
import com.example.common.navigation.FragmentContainerWrapper
import com.example.common.navigation.ScreenNavigator
import com.example.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentContainerWrapper {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    @Inject
    lateinit var dialogManager:DialogManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            screenNavigator.openBlankFragment2()
        }

        dialogManager.showSomeInfo("info")
        Log.d("cnrd",dialogManager.toString())
    }

    override fun getFragmentContainer(): ViewGroup {
        return findViewById(R.id.fragment_container_view)
    }

}