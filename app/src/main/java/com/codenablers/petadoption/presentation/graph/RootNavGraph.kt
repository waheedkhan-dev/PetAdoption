package com.codenablers.petadoption.presentation.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.codenablers.petadoption.domain.datamodel.Pets
import com.codenablers.petadoption.presentation.screens.detail.PetDetailScreen
import com.codenablers.petadoption.presentation.screens.pet.PetScreen
import com.codenablers.petadoption.presentation.screens.pet.PetViewModel


@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    petViewModel: PetViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME

    ) {
        composable(route = Graph.HOME) {
            PetScreen(navController, petViewModel)
        }
        detailsNavGraph(navController)
    }
}

fun NavGraphBuilder.detailsNavGraph(
    navController: NavHostController,

    ) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.PetDetailScreen.route
    ) {
        composable(route = DetailsScreen.PetDetailScreen.route) { _ ->
            val prettyPet =
                navController.previousBackStackEntry?.savedStateHandle?.get<Pets.Hit>("prettyPet")
            prettyPet?.let { PetDetailScreen(navController = navController, it) }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object PetDetailScreen : DetailsScreen(route = "Detail")
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}
