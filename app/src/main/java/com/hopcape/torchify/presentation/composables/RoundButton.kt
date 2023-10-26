package com.hopcape.torchify.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    text: String = "LED",
    turnedOn: Boolean = false,
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = if (turnedOn) Color.Yellow else Color.LightGray
            ),
        contentAlignment = Alignment.Center
        ) {
        Text(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                ),
            text = text,
            style = TextStyle(
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun RoundButtonPreview() {
    RoundButton()
}