@file:OptIn(ExperimentalMaterial3Api::class)

package com.codenablers.petadoption.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codenablers.petadoption.presentation.graph.RootNavigationGraph
import com.codenablers.petadoption.ui.theme.PetAdoptionTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootScreen(navController: NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    /*val currentScreen = NavRoutes.valueOf(
        backStackEntry?.destination?.route ?: NavRoutes.WelcomeScreen.route
    )*/
    val context = LocalContext.current
    PetAdoptionTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            RootNavigationGraph(navController = navController)

        }
    }

}

