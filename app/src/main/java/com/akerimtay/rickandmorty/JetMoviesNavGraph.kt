package com.akerimtay.rickandmorty

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.navigation
import com.akerimtay.rickandmorty.character.CHARACTER_ID_KEY
import com.akerimtay.rickandmorty.character.CharacterDetailScreen
import com.akerimtay.rickandmorty.character.CharactersScreen
import com.akerimtay.rickandmorty.common.lifecycleIsResumed
import com.akerimtay.rickandmorty.episode.EpisodesScreen
import com.akerimtay.rickandmorty.location.LocationsScreen
import com.akerimtay.rickandmorty.main.MainNavigationItem
import com.akerimtay.rickandmorty.settings.SettingsScreen

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val CHARACTER_DETAIL_ROUTE = "character_detail"
}

@Composable
fun JetMoviesNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = MainDestinations.MAIN_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        navigation(
            route = MainDestinations.MAIN_ROUTE,
            startDestination = MainNavigationItem.Characters.route
        ) {
            composable(MainNavigationItem.Characters.route) { from ->
                CharactersScreen(
                    onCharacterClick = { characterId ->
                        openCharacterDetail(
                            backStackEntry = from,
                            navController = navController,
                            characterId = characterId
                        )
                    }
                )
            }
            composable(MainNavigationItem.Locations.route) {
                LocationsScreen()
            }
            composable(MainNavigationItem.Episodes.route) {
                EpisodesScreen()
            }
            composable(MainNavigationItem.Settings.route) {
                SettingsScreen()
            }
        }
        composable(
            route = "${MainDestinations.CHARACTER_DETAIL_ROUTE}/{$CHARACTER_ID_KEY}",
            arguments = listOf(navArgument(CHARACTER_ID_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val characterId = arguments.getLong(CHARACTER_ID_KEY)
            CharacterDetailScreen(
                characterId = characterId,
                upPress = { navController.navigateUp() }
            )
        }
    }
}

private fun openCharacterDetail(
    backStackEntry: NavBackStackEntry,
    navController: NavHostController,
    characterId: Long
) {
    if (backStackEntry.lifecycleIsResumed()) {
        navController.navigate("${MainDestinations.CHARACTER_DETAIL_ROUTE}/$characterId")
    }
}