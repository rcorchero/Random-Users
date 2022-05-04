package com.rcorchero.randomusers.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rcorchero.randomusers.R
import com.rcorchero.randomusers.ui.designsystem.text.Body1
import com.rcorchero.randomusers.ui.designsystem.text.Header1

@Composable
@Preview
fun ErrorScreenPreview() {
    ErrorScreen(reload = {})
}

@Composable
fun ErrorScreen(
    reload: () -> Unit
) = Column(
    modifier = Modifier
        .background(color = Color.White)
        .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Header1(text = stringResource(id = R.string.error_screen_title))
    Button(
        modifier = Modifier.padding(top = 20.dp),
        onClick = { reload() }
    ) {
        Body1(
            color = Color.White,
            text = stringResource(id = R.string.error_screen_button_reload)
        )
    }
}