package com.rcorchero.randomusers.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rcorchero.randomusers.ui.designsystem.theme.Green60

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    color: Color = Green60,
    text: String
) {
    Surface(
        color = color,
        shape = CircleShape,
        modifier = modifier.padding(horizontal = 2.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(8.dp)
        )
    }
}