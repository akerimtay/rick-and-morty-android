package com.akerimtay.rickandmorty.navigation.destination

import com.akerimtay.rickandmorty.navigation.navigator.NavigationDestination

object MainDestination : NavigationDestination {
    override fun route(): String = MAIN_ROUTE

    private const val MAIN_ROUTE = "main"

    object CharactersDestination : NavigationDestination {
        override fun route(): String = CHARACTERS_ROUTE

        private const val CHARACTERS_ROUTE = "$MAIN_ROUTE/characters"
    }

    object EpisodesDestination : NavigationDestination {
        override fun route(): String = EPISODES_ROUTE

        private const val EPISODES_ROUTE = "$MAIN_ROUTE/episodes"
    }

    object LocationsDestination : NavigationDestination {
        override fun route(): String = LOCATIONS_ROUTE

        private const val LOCATIONS_ROUTE = "$MAIN_ROUTE/locations"
    }

    object SettingsDestination : NavigationDestination {
        override fun route(): String = SETTINGS_ROUTE

        private const val SETTINGS_ROUTE = "$MAIN_ROUTE/settings"
    }
}