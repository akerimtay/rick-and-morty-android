package com.akerimtay.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.akerimtay.navigation.bottomnavigation.BottomNavigationBar
import com.akerimtay.navigation.destination.MainDestination
import com.akerimtay.navigation.navigator.Navigator
import com.akerimtay.navigation.navigator.NavigatorEvent
import com.akerimtay.resources.RickAndMortyTheme
import com.akerimtay.rickandmorty.navigation.addBottomNavigationDestinations
import com.akerimtay.rickandmorty.navigation.addComposableDestinations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                val navController = rememberNavController()
                LaunchedEffect(navController) {
                    navigator.destinations.collect { event ->
                        when (event) {
                            is NavigatorEvent.NavigateUp -> navController.navigateUp()
                            is NavigatorEvent.Directions -> navController.navigate(
                                route = event.destination,
                                builder = event.builder
                            )
                        }
                    }
                }
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) { innerPaddingModifier ->
                    NavHost(
                        navController = navController,
                        startDestination = MainDestination.route(),
                        modifier = Modifier.padding(innerPaddingModifier)
                    ) {
                        addBottomNavigationDestinations()
                        addComposableDestinations()
                    }
                }
            }
        }
    }
}