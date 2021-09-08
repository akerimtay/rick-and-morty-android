package com.akerimtay.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.akerimtay.rickandmorty.character.ui.CharactersScreen
import com.akerimtay.rickandmorty.episode.ui.EpisodesScreen
import com.akerimtay.rickandmorty.location.ui.LocationsScreen
import com.akerimtay.rickandmorty.navigation.bottomnavigation.BottomNavigationItem
import com.akerimtay.rickandmorty.navigation.destination.MainDestination
import com.akerimtay.rickandmorty.settings.ui.SettingsScreen

private val destinationsBottomNav: Map<BottomNavigationItem, @Composable () -> Unit> = mapOf(
    BottomNavigationItem.Characters to { CharactersScreen() },
    BottomNavigationItem.Locations to { LocationsScreen() },
    BottomNavigationItem.Episodes to { EpisodesScreen() },
    BottomNavigationItem.Settings to { SettingsScreen() }
)

fun NavGraphBuilder.addBottomNavigationDestinations() {
    navigation(
        startDestination = BottomNavigationItem.Characters.route,
        route = MainDestination.route()
    ) {
        destinationsBottomNav.forEach { entry ->
            val destination = entry.key
            composable(destination.route) {
                entry.value()
            }
        }
    }
}