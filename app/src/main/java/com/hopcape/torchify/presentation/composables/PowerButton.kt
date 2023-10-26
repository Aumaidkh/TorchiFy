package com.hopcape.torchify.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hopcape.torchify.R

@Composable
fun PowerButton(
    modifier: Modifier = Modifier,
    turnedOn: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
               onClick()
            },
        shape = CircleShape,
        color = if (turnedOn) Color.Yellow else Color.DarkGray,
        shadowElevation = 20.dp
    ) {
        Icon(
            modifier = Modifier
                .padding(86.dp)
                .size(54.dp),
            painter = painterResource(id = R.drawable.power),
            contentDescription = "Power Icon",
            tint = if (turnedOn) Color.Blue else Color.Yellow
        )
    }
}

@Preview
@Composable
fun PowerButtonOnPreview() {
    PowerButton(
        turnedOn = true
    )
}

@Preview
@Composable
fun PowerButtonOffPreview() {
    PowerButton(
        turnedOn = false
    )
}