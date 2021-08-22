package com.akerimtay.rickandmorty.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akerimtay.rickandmorty.JetMoviesNavGraph
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.Routes
import com.akerimtay.rickandmorty.ui.theme.Gray300

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPaddingModifier ->
        JetMoviesNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        MainNavigationItem.Characters,
        MainNavigationItem.Locations,
        MainNavigationItem.Episodes,
        MainNavigationItem.Settings
    )
    val routes = items.map { it.route }
    if (currentRoute in routes) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.title)
                        )
                    },
                    label = { Text(text = stringResource(id = item.title)) },
                    selectedContentColor = MaterialTheme.colors.secondary,
                    unselectedContentColor = Gray300,
                    selected = currentRoute == item.route,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    }
}

sealed class MainNavigationItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Characters : MainNavigationItem(
        title = R.string.characters_title,
        icon = R.drawable.ic_character_grey,
        route = Routes.MAIN_CHARACTER
    )

    object Locations : MainNavigationItem(
        title = R.string.locations_title,
        icon = R.drawable.ic_locations_grey,
        route = Routes.MAIN_LOCATION
    )

    object Episodes : MainNavigationItem(
        title = R.string.episodes_title,
        icon = R.drawable.ic_episodes_grey,
        route = Routes.MAIN_EPISODE
    )

    object Settings : MainNavigationItem(
        title = R.string.settings_title,
        icon = R.drawable.ic_settings_grey,
        route = Routes.MAIN_SETTINGS
    )
}