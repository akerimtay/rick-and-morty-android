package com.akerimtay.rickandmorty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.akerimtay.rickandmorty.characterDetail.ui.CharacterDetailScreen
import com.akerimtay.rickandmorty.navigation.destination.CharacterDetailsDestination
import com.akerimtay.rickandmorty.navigation.navigator.NavigationDestination

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