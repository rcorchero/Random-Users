package com.rcorchero.randomusers.feature.dashboard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.rcorchero.randomusers.common.AssertionUtils.assertBackgroundColor
import com.rcorchero.randomusers.common.FakeData.mockDashboardModel
import com.rcorchero.randomusers.ui.designsystem.theme.RandomUsersTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DashboardActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RandomUsersTheme {
                MainScreen(model = mockDashboardModel())
            }
        }
    }

    @Test
    fun checkBackgroundColor() {
        composeTestRule.onNodeWithTag("MainScreen").assertBackgroundColor(Color.LightGray)
    }

    @Test
    fun screenShouldShowDashboardTitle() {
        composeTestRule.onNodeWithText("Random Users").assertIsDisplayed()
    }
}