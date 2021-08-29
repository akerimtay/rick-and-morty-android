package com.akerimtay.character_details

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

@Composable
fun CharacterDetailScreen() {
    val viewModel = hiltViewModel<CharacterDetailsViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Character Detail",
            color = MaterialTheme.colors.secondary
        )
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
            onClick = { viewModel.navigateUp() }
        ) {
            Text(
                text = "navigate Up",
                color = MaterialTheme.colors.onSecondary
            )
        }
    }
}