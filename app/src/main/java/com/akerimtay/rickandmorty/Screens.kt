package com.akerimtay.rickandmorty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akerimtay.navigation.NavigatorViewModel
import com.akerimtay.navigation.destination.CharacterDetailsDestination
import kotlin.random.Random

@Composable
fun LocationsScreen() {
    val navigator = hiltViewModel<NavigatorViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Text(
            text = "Locations Screen",
            color = MaterialTheme.colors.secondary
        )
        val characterId = Random.nextLong(0, 100)
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            onClick = { navigator.navigate(CharacterDetailsDestination.createCharacterDetailsRoute(characterId)) })
        {
            Text(
                text = "Open details",
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
fun EpisodesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Episodes Screen",
            color = MaterialTheme.colors.secondary
        )
    }
}

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Settings Screen",
            color = MaterialTheme.colors.secondary
        )
    }
}