package com.rcorchero.randomusers.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.rcorchero.randomusers.ui.designsystem.theme.Purple700


@Composable
fun ButtonIcon(
    onClick: () -> Unit,
    imageVector: ImageVector,
    backgroundColor: Color = Purple700,
    contentDescription: String,
    tint: Color = Color.White
) {
    IconButton(onClick = { onClick() }) {
        Image(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            imageVector = imageVector,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(tint),
            contentScale = ContentScale.Inside
        )
    }
}
