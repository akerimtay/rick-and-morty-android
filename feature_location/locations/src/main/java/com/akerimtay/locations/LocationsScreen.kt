package com.akerimtay.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.akerimtay.rickandmorty.navigation.NavigatorViewModel
import com.akerimtay.rickandmorty.navigation.destination.CharacterDetailsDestination
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
                text = "Open character details",
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}