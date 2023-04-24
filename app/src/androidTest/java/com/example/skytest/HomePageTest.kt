package com.example.skytest

import android.content.res.Resources
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.skytest.ui.theme.SkyTestTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomePageTest {

    @get:Rule (order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule (order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var  resources: Resources

    @Before
    fun setUp() {
        hiltRule.inject()
        resources = composeTestRule.activity.resources
        composeTestRule.activity.setContent {
            SkyTestTheme {
                HomeScreen()
            }
        }
    }

    @Test
    fun test_search_bar_can_input_text() {
        composeTestRule.onNodeWithText(text = resources.getString(R.string.search))
            .performClick()
            .performTextInput("al")

        composeTestRule.onNodeWithText(text = resources.getString(R.string.search)).assertTextContains("al")
    }

    @Test
    fun test_movie_grid_is_displayed(){
        composeTestRule.onNodeWithTag("lazyGrid").assertExists()
    }
}
