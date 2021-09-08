package com.akerimtay.domain.usecase

import com.akerimtay.domain.model.Characters
import com.akerimtay.domain.repository.CharacterRepository
import com.akerimtay.rickandmorty.common.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : UseCase<Unit, Characters>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): Characters = characterRepository.getCharacters()
}