package com.movie.moviedb

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.testing.TestNavHostController
import com.movie.moviedb.ui.theme.MovieDbTheme
import com.movie.moviedb.ui.view.DetailScreen
import com.movie.moviedb.ui.view.HomeScreen
import com.movie.moviedb.ui.viewmodel.MovieViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.Mockito


class MovieNavTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testNavigationToHomeScreen() {
        val navController = TestNavHostController(composeTestRule.activity)
        val viewModel = Mockito.mock(MovieViewModel::class.java)

        composeTestRule.setContent {
            MovieDbTheme {
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }

        navController.navigate("home")
        assert(navController.currentDestination?.route == "home")
    }

    @Test
    fun testNavigationToDetailScreen() {
        val navController = TestNavHostController(composeTestRule.activity)
        val viewModel = Mockito.mock(MovieViewModel::class.java)

        composeTestRule.setContent {
            MovieDbTheme {
                NavHost(navController = navController, startDestination = "details") {
                    composable("details") {
                        DetailScreen(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }

        navController.navigate("details")
        assert(navController.currentDestination?.route == "details")
    }

}