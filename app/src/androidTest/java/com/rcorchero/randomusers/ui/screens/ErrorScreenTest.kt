package com.rcorchero.randomusers.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rcorchero.randomusers.feature.dashboard.DashboardActivity
import com.rcorchero.randomusers.ui.designsystem.theme.RandomUsersTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DashboardActivity>()

    @Before
    fun setUp(){
        composeTestRule.setContent {
            RandomUsersTheme {
                ErrorScreen(reload = {})
            }
        }
    }

    @Test
    fun showMainDescription() {
        composeTestRule.onNodeWithText("An error has occurred. Try again later.").assertIsDisplayed()
    }

    @Test
    fun showReloadButton() {
        composeTestRule.onNodeWithText("Reload").assertIsDisplayed()
    }
}