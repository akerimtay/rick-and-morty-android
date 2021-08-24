package com.akerimtay.rickandmorty.character.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    onCharacterClick: (Long) -> Unit
) {
    val isLoading: Boolean by viewModel.isLoading.observeAsState(true)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

    }
}