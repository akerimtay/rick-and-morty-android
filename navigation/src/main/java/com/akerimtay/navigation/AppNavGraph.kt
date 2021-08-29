package com.akerimtay.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

sealed class Screen(open val route: String, val arguments: List<NamedNavArgument>) {
    object Main : Screen(
        route = "main",
        arguments = emptyList()
    ) {
        object Character : Screen(
            route = "main/characters",
            arguments = emptyList()
        )

        object Location : Screen(
            route = "main/locations",
            arguments = emptyList()
        )

        object Episode : Screen(
            route = "main/episodes",
            arguments = emptyList()
        )

        object Settings : Screen(
            route = "main/settings",
            arguments = emptyList()
        )
    }

    object CharacterDetail : Screen(
        route = "character_detail",
//        arguments = listOf(navArgument(CharacterDetailParameters.CHARACTER_ID_KEY) {
//            type = NavType.LongType
//        })
        arguments = emptyList()
    )
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Screen.Main.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = Screen.Main.route,
            startDestination = Screen.Main.Character.route
        ) {
            composable(BottomNavigationItem.Characters.route) { from ->
//                val viewModel = hiltViewModel<CharactersViewModel>()
//                CharactersScreen(
//                    viewModel = viewModel,
//                    onCharacterClick = { characterId ->
//                        openCharacterDetail(
//                            backStackEntry = from,
//                            navController = navController,
//                            characterId = characterId
//                        )
//                    }
//                )
            }
            composable(BottomNavigationItem.Locations.route) {
//                LocationsScreen()
            }
            composable(BottomNavigationItem.Episodes.route) {
//                EpisodesScreen()
            }
            composable(BottomNavigationItem.Settings.route) {
//                SettingsScreen()
            }
        }
        composable(
//            route = "${Screen.CharacterDetail.route}/{${CharacterDetailParameters.CHARACTER_ID_KEY}}",
            route = "",
            arguments = Screen.CharacterDetail.arguments
        ) { backStackEntry ->

        }
    }
}

private fun openCharacterDetail(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
    characterId: Long
) {
    if (backStackEntry.lifecycleIsResumed()) {
        navController.navigate("${Screen.CharacterDetail.route}/$characterId")
    }
}