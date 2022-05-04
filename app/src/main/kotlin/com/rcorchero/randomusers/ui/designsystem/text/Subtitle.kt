package com.rcorchero.randomusers.ui.designsystem.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.rcorchero.randomusers.ui.designsystem.theme.DarkGrey
import com.rcorchero.randomusers.ui.designsystem.theme.Typography

@Composable
fun Subtitle1(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = DarkGrey,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        style = Typography.subtitle1,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}