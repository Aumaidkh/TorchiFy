package com.hopcape.torchify.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopcape.torchify.R
import com.hopcape.torchify.presentation.composables.PowerButton
import com.hopcape.torchify.presentation.composables.RoundButton

/**
 * Screen with black background containing
 * 1. Sun Icon
 * 2. FlashLight State
 * 3. Power Button*/
@Composable
fun HomeScreen(
    turnOnOrOffFlashLight: (Boolean) -> Unit = {}
){
    var flashLightOn by remember {
        mutableStateOf(false)
    }

    var lightSource by remember {
        mutableStateOf<LightSource>(LightSource.Led)
    }

    var screenBackground by remember {
        mutableStateOf(Color.Black)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = screenBackground
            )
            .padding(
                vertical = 100.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.sun),
            contentDescription = "SunIcon",
            colorFilter = if (!flashLightOn) ColorFilter.tint(color = Color.Gray) else null
        )

        Text(
            text = stringResource(
                id = R.string.flashlight_turned,
                if (flashLightOn)
                    stringResource(id = R.string.on)
                else stringResource(id = R.string.off)),
            style = TextStyle(
                color = if (screenBackground == Color.White) Color.Gray else Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
        )
        PowerButton(
            turnedOn = flashLightOn,
            onClick = {
                flashLightOn = !flashLightOn
                when(lightSource){
                    is LightSource.Screen -> {
                        screenBackground = if (screenBackground == Color.White) Color.Black else Color.White
                    }
                    is LightSource.Led -> {
                        turnOnOrOffFlashLight(flashLightOn)
                    }
                }
            }
        )

        // Led Button
        RoundButton(
            text = stringResource(id = R.string.led),
            modifier = Modifier
                .width(100.dp)
                .clickable {
                    flashLightOn = false
                    screenBackground = Color.Black
                    lightSource = LightSource.Led
                },
            turnedOn = lightSource is LightSource.Led
        )
        // Screen Button
        RoundButton(
            text = stringResource(id = R.string.screen),
            modifier = Modifier
                .width(100.dp)
                .clickable {
                    flashLightOn = false
                    screenBackground = Color.Black
                    lightSource = LightSource.Screen
                },
            turnedOn = lightSource is LightSource.Screen
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

sealed interface LightSource{
    object Led: LightSource
    object Screen: LightSource
}