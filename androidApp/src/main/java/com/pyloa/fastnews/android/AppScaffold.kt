package com.pyloa.fastnews.android

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pyloa.fastnews.android.router.Routes
import com.pyloa.fastnews.android.screens.AboutScreen
import com.pyloa.fastnews.android.screens.ArticlesScreen
import com.pyloa.fastnews.articles.ArticlesViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    viewModel: ArticlesViewModel
) {
    val navController = rememberNavController()

    Scaffold {
        AppNavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            viewModel
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier, viewModel: ArticlesViewModel
) {
    NavHost(navController = navController,
        startDestination = Routes.ARTICLES.route,
        modifier = modifier,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(100, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideOutOfContainer(
                animationSpec = tween(100, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }) {
        composable(Routes.ARTICLES.route) {
            ArticlesScreen(viewModel = viewModel, onAboutButtonClick = {
                navController.navigate(Routes.ABOUT.route)
            })
        }
        composable(Routes.ABOUT.route) {
            AboutScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}
