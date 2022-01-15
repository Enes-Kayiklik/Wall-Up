package com.eneskayiklik.wallup.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.feature_bookmark.presentation.bookmarkScreen
import com.eneskayiklik.wallup.feature_collection.presentation.collectionScreen
import com.eneskayiklik.wallup.feature_detail.presentation.detailScreen
import com.eneskayiklik.wallup.feature_home.presentation.homeScreen
import com.eneskayiklik.wallup.feature_splash.presentation.splashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@ExperimentalUnitApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun ApplicationNavigation(
    controller: NavHostController
) {
    AnimatedNavHost(navController = controller, startDestination = Destinations.Splash.route) {
        splashScreen(controller::popBackStack) {
            controller.navigate(it) { launchSingleTop = true }
        }
        homeScreen {
            controller.navigate(it) { launchSingleTop = true }
        }
        detailScreen(controller::popBackStack) {
            controller.navigate(it) { launchSingleTop = true }
        }
        collectionScreen {
            controller.navigate(it) { launchSingleTop = true }
        }
        bookmarkScreen {
            controller.navigate(it) { launchSingleTop = true }
        }
    }
}