package com.rcorchero.randomusers.feature.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rcorchero.domain.model.User
import com.rcorchero.presentation.feature.dashboard.DashboardModel
import com.rcorchero.presentation.feature.dashboard.DashboardState
import com.rcorchero.presentation.feature.dashboard.DashboardStore
import com.rcorchero.randomusers.R
import com.rcorchero.randomusers.ui.designsystem.text.Body1
import com.rcorchero.randomusers.ui.designsystem.text.Header1
import com.rcorchero.randomusers.ui.designsystem.text.Subtitle1
import com.rcorchero.randomusers.ui.designsystem.theme.getRandomColor
import com.rcorchero.randomusers.ui.screens.ErrorScreen
import com.rcorchero.randomusers.ui.screens.LoadingScreen

@Composable
fun DashboardScreen(
    store: DashboardStore
) {
    val state: DashboardState by store.getState().collectAsState()

    val status by remember { derivedStateOf { state.status } }

    when (status) {
        DashboardState.Status.LOADING -> LoadingScreen()
        DashboardState.Status.ERROR -> ErrorScreen(reload = { store.onInit() })
        DashboardState.Status.LOADED -> MainScreen(model = state.model)
    }
}

@Composable
fun MainScreen(
    model: DashboardModel
) = Column(
    modifier = Modifier
        .testTag("MainScreen")
        .background(Color.LightGray)
        .padding(all = 16.dp)
) {
    Header1(text = stringResource(id = R.string.dashboard_title))
    LazyColumn(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        items(model.users) { user ->
            UserRow(
                user = user,
                onClick = model.goToDetail
            )
        }
    }
}

@Composable
private fun UserRow(
    user: User,
    onClick: (Int) -> Unit
) = Row(
    modifier = Modifier
        .testTag("UserRow")
        .fillMaxWidth()
        .padding(6.dp)
        .clickable { onClick(user.id) }
) {
    Image(
        modifier = Modifier
            .size(64.dp)
            .align(Alignment.CenterVertically)
            .clip(CircleShape)
            .background(getRandomColor()),
        imageVector = Icons.Default.Face,
        contentDescription = "Face icon",
        contentScale = ContentScale.Crop
    )
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Subtitle1(text = user.name)
        Body1(text = user.username)
    }
}