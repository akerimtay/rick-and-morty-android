package com.akerimtay.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.akerimtay.navigation.destination.CharacterDetailsDestination
import com.akerimtay.navigation.navigator.NavigationDestination
import com.akerimtay.rickandmorty.CharacterDetailScreen

private val composableDestinations: Map<NavigationDestination, @Composable () -> Unit> = mapOf(
    CharacterDetailsDestination to { CharacterDetailScreen() },
)

fun NavGraphBuilder.addComposableDestinations() {
    composableDestinations.forEach { entry ->
        val destination = entry.key
        composable(destination.route(), destination.arguments, destination.deepLinks) {
            entry.value()
        }
    }
}