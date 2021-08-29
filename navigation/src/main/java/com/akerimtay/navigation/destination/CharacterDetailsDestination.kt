package com.akerimtay.navigation.destination

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.akerimtay.navigation.navigator.NavigationDestination

object CharacterDetailsDestination : NavigationDestination {
    override fun route(): String = CHARACTER_DETAILS_ROUTE

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(CHARACTER_ID_PARAM) { type = NavType.LongType })

    const val CHARACTER_ID_PARAM = "characterId"
    private const val CHARACTER_DETAILS = "character_detail"
    private const val CHARACTER_DETAILS_ROUTE = "character_detail/{$CHARACTER_ID_PARAM}"

    fun createCharacterDetailsRoute(characterId: Long) = "$CHARACTER_DETAILS/$characterId"
}