package com.akerimtay.rickandmorty.episode.domain

import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.usecase.UseCase
import com.akerimtay.rickandmorty.episode.CharacterContract
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class GetCharactersUseCase @Inject constructor(
    private val characterContract: CharacterContract
) : UseCase<Unit, List<Character>>() {

    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): List<Character> {
        return characterContract.getCharacters()
    }
}