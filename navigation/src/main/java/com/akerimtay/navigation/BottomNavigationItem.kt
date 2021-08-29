package com.akerimtay.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class BottomNavigationItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Characters : BottomNavigationItem(
        title = R.string.characters_title,
        icon = R.drawable.ic_character_grey,
        route = Screen.Main.Character.route
    )

    object Locations : BottomNavigationItem(
        title = R.string.locations_title,
        icon = R.drawable.ic_locations_grey,
        route = Screen.Main.Location.route
    )

    object Episodes : BottomNavigationItem(
        title = R.string.episodes_title,
        icon = R.drawable.ic_episodes_grey,
        route = Screen.Main.Episode.route
    )

    object Settings : BottomNavigationItem(
        title = R.string.settings_title,
        icon = R.drawable.ic_settings_grey,
        route = Screen.Main.Settings.route
    )
}