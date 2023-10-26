package com.hopcape.torchify

import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.hopcape.torchify.presentation.screen.HomeScreen
import com.hopcape.torchify.ui.theme.TorchiFyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
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

}
