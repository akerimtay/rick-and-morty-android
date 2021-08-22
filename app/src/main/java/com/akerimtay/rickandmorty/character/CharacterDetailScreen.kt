package com.akerimtay.rickandmorty.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

object CharacterDetailParameters {
    const val CHARACTER_ID_KEY = "characterId"
}

@Composable
fun CharacterDetailScreen(
    characterId: Long,
    upPress: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

    }
}