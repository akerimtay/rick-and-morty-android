package com.akerimtay.resources

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = BlueGray800,
    primaryVariant = BlueGray900,
    secondary = LightGreen400,
    secondaryVariant = Green500,
    background = BlueGray900,
    error = Red400
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = Gray50,
    secondary = Cyan400,
    secondaryVariant = Cyan400,
    background = Gray50,
    error = Red400
)

@Composable
fun RickAndMortyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    val sysUiController = rememberSystemUiController()
    SideEffect {
        sysUiController.setSystemBarsColor(color = colors.primaryVariant)
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}