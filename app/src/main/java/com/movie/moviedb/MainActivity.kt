package com.movie.moviedb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.movie.moviedb.ui.theme.Grey80
import com.movie.moviedb.ui.theme.MovieDbTheme
import com.movie.moviedb.ui.view.DetailScreen
import com.movie.moviedb.ui.view.HomeScreen
import com.movie.moviedb.ui.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MovieDbTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Grey80,//MaterialTheme.colorScheme.background
                    contentColor = Color.White

                ) {

                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "home") {

                       composable("home",
                            enterTransition = {
                                when (initialState.destination.route) {
                                    "details" ->
                                        slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            exitTransition = {
                                when (targetState.destination.route) {
                                    "details" ->
                                        slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            popEnterTransition = {
                                when (initialState.destination.route) {
                                    "details" ->
                                        slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            popExitTransition = {
                                when (targetState.destination.route) {
                                    "details" ->
                                        slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            }
                        ){

                            HomeScreen(viewModel = viewModel, navController = navController)
                        }
                        composable("details",
                            enterTransition = {
                                when (initialState.destination.route) {
                                    "details" ->
                                        slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            exitTransition = {
                                when (targetState.destination.route) {
                                    "home" ->
                                        slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Left,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            popEnterTransition = {
                                when (initialState.destination.route) {
                                    "details" ->
                                        slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            },
                            popExitTransition = {
                                when (targetState.destination.route) {
                                    "home" ->
                                        slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Right,
                                            animationSpec = tween(700)
                                        )

                                    else -> null
                                }
                            }

                            ){
                            DetailScreen(viewModel = viewModel, navController=navController)
                        }
                    }

                }
            }
        }
    }
}



