package com.rcorchero.randomusers.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
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
import com.rcorchero.presentation.feature.detail.DetailState
import com.rcorchero.presentation.feature.detail.DetailStore
import com.rcorchero.randomusers.R
import com.rcorchero.randomusers.ui.components.ButtonIcon
import com.rcorchero.randomusers.ui.components.Chip
import com.rcorchero.randomusers.ui.components.TopBar
import com.rcorchero.randomusers.ui.designsystem.text.Body1
import com.rcorchero.randomusers.ui.designsystem.text.Header1
import com.rcorchero.randomusers.ui.designsystem.text.Header2
import com.rcorchero.randomusers.ui.designsystem.text.Subtitle1
import com.rcorchero.randomusers.ui.designsystem.theme.getRandomColor
import com.rcorchero.randomusers.ui.screens.ErrorScreen
import com.rcorchero.randomusers.ui.screens.LoadingScreen

@Composable
fun DetailScreen(
    store: DetailStore
) {
    val state: DetailState by store.getState().collectAsState()

    val status by remember { derivedStateOf { state.status } }

    when (status) {
        DetailState.Status.LOADING -> LoadingScreen()
        DetailState.Status.ERROR -> ErrorScreen(reload = { store.onInit() })
        DetailState.Status.LOADED ->
            with(state.model) {
                MainScreen(
                    user = user,
                    onBackClicked = goBack,
                    onPhoneClicked = makePhoneCall,
                    onEmailClicked = sendEmail,
                    onLocationClicked = showLocation
                )
            }
    }
}

@Composable
fun MainScreen(
    user: User,
    onBackClicked: () -> Unit,
    onPhoneClicked: () -> Unit,
    onEmailClicked: () -> Unit,
    onLocationClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        backgroundColor = Color.LightGray,
        topBar = {
            TopBar(
                title = stringResource(id = R.string.detail_title),
                onBackClicked = onBackClicked
            )
        }
    ) {
        Column(
            modifier = Modifier
                .testTag("MainScreen")
                .verticalScroll(rememberScrollState())
                .padding(all = 16.dp)
        ) {
            with(user) {
                UserHeader(
                    name = name,
                    username = username
                )
                UserOptions(
                    modifier = Modifier.padding(top = 24.dp),
                    onPhoneClicked = onPhoneClicked,
                    onEmailClicked = onEmailClicked,
                    onLocationClicked = onLocationClicked
                )
                Divider(
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                )
                UserMainData(
                    email = email,
                    address = address.getFullAddress(),
                    phone = phone,
                    website = website
                )
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                UserCompany(
                    company = company.name,
                    tags = company.getTags()
                )
            }
        }
    }
}

@Composable
private fun UserHeader(
    modifier: Modifier = Modifier,
    name: String,
    username: String
) =
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(getRandomColor()),
            imageVector = Icons.Default.Face,
            contentDescription = "Face icon",
            contentScale = ContentScale.Crop
        )
        Header1(
            modifier = Modifier.padding(top = 16.dp),
            text = name
        )
        Header2(
            modifier = Modifier.padding(top = 8.dp),
            text = username
        )
    }

@Composable
private fun UserOptions(
    modifier: Modifier = Modifier,
    onPhoneClicked: () -> Unit,
    onEmailClicked: () -> Unit,
    onLocationClicked: () -> Unit
) =
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ButtonIcon(
            onClick = { onPhoneClicked() },
            imageVector = Icons.Default.Phone,
            contentDescription = "Phone icon"
        )
        ButtonIcon(
            onClick = { onEmailClicked() },
            imageVector = Icons.Default.Email,
            contentDescription = "Email icon"
        )
        ButtonIcon(
            onClick = { onLocationClicked() },
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Location icon"
        )
    }

@Composable
private fun UserMainData(
    modifier: Modifier = Modifier,
    email: String,
    address: String,
    phone: String,
    website: String
) =
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RowInfo(
            title = stringResource(id = R.string.detail_user_email),
            description = email
        )
        RowInfo(
            modifier = Modifier.padding(top = 8.dp),
            title = stringResource(id = R.string.detail_user_address),
            description = address
        )
        RowInfo(
            modifier = Modifier.padding(top = 8.dp),
            title = stringResource(id = R.string.detail_user_phone),
            description = phone
        )
        RowInfo(
            modifier = Modifier.padding(top = 8.dp),
            title = stringResource(id = R.string.detail_user_website),
            description = website
        )
    }

@Composable
private fun UserCompany(
    modifier: Modifier = Modifier,
    company: String,
    tags: List<String>
) =
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RowInfo(title = stringResource(id = R.string.detail_user_company), description = company)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tags.forEach { Chip(text = it) }
        }
    }

@Composable
private fun RowInfo(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) = Row(modifier = modifier) {
    Subtitle1(text = "$title ")
    Body1(text = description)
}