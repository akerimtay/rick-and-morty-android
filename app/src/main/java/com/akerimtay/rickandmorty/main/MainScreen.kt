package com.akerimtay.rickandmorty.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.akerimtay.rickandmorty.JetMoviesNavGraph
import com.akerimtay.rickandmorty.R
import com.akerimtay.rickandmorty.ui.theme.Purple500

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
            backgroundColor = Purple500,
            contentColor = Color.White
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
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
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
    object Characters : MainNavigationItem(R.string.characters_title, R.drawable.ic_character_grey, "main/character")
    object Locations : MainNavigationItem(R.string.locations_title, R.drawable.ic_locations_grey, "main/locations")
    object Episodes : MainNavigationItem(R.string.episodes_title, R.drawable.ic_episodes_grey, "main/episodes")
    object Settings : MainNavigationItem(R.string.settings_title, R.drawable.ic_settings_grey, "main/settings")
}