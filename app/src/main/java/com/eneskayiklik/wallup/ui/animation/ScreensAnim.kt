package com.eneskayiklik.wallup.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavBackStackEntry
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.destinations.SplashScreenDestination
import com.eneskayiklik.wallup.navDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalUnitApi
@ExperimentalCoilApi
object ScreensAnim : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return when (initialState.navDestination) {
            SplashScreenDestination -> null
            else -> slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it })
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition {
        return slideOutHorizontally(
            animationSpec = tween(300),
            targetOffsetX = { (-it / 3) * 2 }) + fadeOut(
            animationSpec = tween(durationMillis = 300), targetAlpha = 0.3F
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popEnterTransition(): EnterTransition {
        return slideInHorizontally(animationSpec = tween(300), initialOffsetX = { -it }) + fadeIn(
            animationSpec = tween(durationMillis = 300), initialAlpha = 0.3F
        )
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition {
        return slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { it })
    }
}