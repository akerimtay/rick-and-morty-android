package com.akerimtay.rickandmorty.character.domain

import com.akerimtay.common.base.UseCase
import com.akerimtay.rickandmorty.character.model.Characters
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetCharactersUseCase @Inject constructor(
    private val characterRemoteGateway: CharacterRemoteGateway
) : UseCase<Unit, Characters>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): Characters = characterRemoteGateway.getCharacters()
}