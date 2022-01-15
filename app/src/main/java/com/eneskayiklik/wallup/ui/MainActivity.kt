package com.eneskayiklik.wallup.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import coil.annotation.ExperimentalCoilApi
import com.eneskayiklik.wallup.NavGraphs
import com.eneskayiklik.wallup.ui.navigation.ApplicationNavigation
import com.eneskayiklik.wallup.ui.theme.WallUpTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalUnitApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WallUpTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
                // ApplicationNavigation(controller = rememberAnimatedNavController())
            }
        }
    }
}