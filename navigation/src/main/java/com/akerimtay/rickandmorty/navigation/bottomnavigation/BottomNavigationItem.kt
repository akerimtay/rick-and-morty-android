package com.akerimtay.rickandmorty.navigation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.akerimtay.rickandmorty.navigation.R
import com.akerimtay.rickandmorty.navigation.destination.MainDestination

sealed class BottomNavigationItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Characters : BottomNavigationItem(
        title = R.string.characters_title,
        icon = R.drawable.ic_character_grey,
        route = MainDestination.CharactersDestination.route()
    )

    object Locations : BottomNavigationItem(
        title = R.string.locations_title,
        icon = R.drawable.ic_locations_grey,
        route = MainDestination.LocationsDestination.route()
    )

    object Episodes : BottomNavigationItem(
        title = R.string.episodes_title,
        icon = R.drawable.ic_episodes_grey,
        route = MainDestination.EpisodesDestination.route()
    )

    object Settings : BottomNavigationItem(
        title = R.string.settings_title,
        icon = R.drawable.ic_settings_grey,
        route = MainDestination.SettingsDestination.route()
    )
}