package com.rcorchero.randomusers.feature.detail

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.rcorchero.randomusers.common.AssertionUtils.assertBackgroundColor
import com.rcorchero.randomusers.common.FakeData
import com.rcorchero.randomusers.ui.designsystem.theme.RandomUsersTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DetailActivity>()

    private val data = FakeData.mockDashboardModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            RandomUsersTheme {
                MainScreen(
                    user = data.users.first(),
                    onBackClicked = {},
                    onPhoneClicked = {},
                    onEmailClicked = {},
                    onLocationClicked = {}
                )
            }
        }
    }

    @Test
    fun checkBackgroundColor() {
        composeTestRule.onNodeWithTag("MainScreen").assertBackgroundColor(Color.DarkGray)
    }

    @Test
    fun screenShouldShowTopBarTitle() {
        composeTestRule.onNodeWithText("User Details").assertIsDisplayed()
    }

    @Test
    fun screenShouldUserOptions() {
        composeTestRule.onNodeWithContentDescription("Phone icon").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Email icon").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Location icon").assertIsDisplayed()
    }
}