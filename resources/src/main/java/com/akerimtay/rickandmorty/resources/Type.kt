package com.akerimtay.rickandmorty.resources

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val Roboto = FontFamily(
    Font(resId = R.font.roboto_black, weight = FontWeight.Black),
    Font(resId = R.font.roboto_bold, weight = FontWeight.Bold),
    Font(resId = R.font.roboto_light, weight = FontWeight.Light),
    Font(resId = R.font.roboto_medium, weight = FontWeight.Medium),
    Font(resId = R.font.roboto_regular, weight = FontWeight.Normal),
    Font(resId = R.font.roboto_thin, weight = FontWeight.Thin)
)

val Typography = Typography(defaultFontFamily = Roboto)