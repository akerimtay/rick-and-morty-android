package com.akerimtay.rickandmorty.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.akerimtay.rickandmorty.ui.theme.Purple200
import com.akerimtay.rickandmorty.ui.theme.Purple500

const val CHARACTER_ID_KEY = "characterId"

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
        Text(
            text = "Character Detail Screen",
            fontWeight = FontWeight.Bold,
            color = Purple500,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Text(
            text = "id = $characterId",
            fontWeight = FontWeight.Medium,
            color = Purple200,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Button(
            onClick = upPress,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Back")
        }
    }
}