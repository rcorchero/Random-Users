package com.rcorchero.randomusers.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rcorchero.randomusers.ui.designsystem.text.Header1

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClicked: () -> Unit
) =
    Row(modifier = modifier.padding(all = 16.dp)) {
        Image(
            modifier = Modifier
                .clickable { onBackClicked() }
                .align(Alignment.CenterVertically),
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Arrow back icon"
        )
        Header1(
            modifier = Modifier.padding(start = 16.dp),
            text = title
        )
    }