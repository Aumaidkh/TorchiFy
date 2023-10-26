package com.hopcape.torchify

import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.hopcape.torchify.presentation.screen.HomeScreen
import com.hopcape.torchify.ui.theme.TorchiFyTheme
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.BuildConfig
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        initAppCenter()
        setContent {
            TorchiFyTheme {
                HomeScreen(
                    turnOnOrOffFlashLight = { turnOn ->
                        val cameraManager = getSystemService(CameraManager::class.java)
                        val cameraId = cameraManager.cameraIdList[0]
                        try {
                            cameraManager.setTorchMode(cameraId,turnOn)
                        } catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                )
            }
        }
    }

    private fun initAppCenter(){
        AppCenter.start(application, com.hopcape.torchify.BuildConfig.APP_CENTER_SECRET,
            Analytics::class.java, Crashes::class.java)
    }

}
