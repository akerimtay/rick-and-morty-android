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
import com.akerimtay.rickandmorty.ui.theme.Purple500

@Composable
fun CharactersScreen(
    onCharacterClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Characters Screen",
            fontWeight = FontWeight.Bold,
            color = Purple500,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onCharacterClick(1) },
        ) {
            Text(text = "Open Detail")
        }
    }
}