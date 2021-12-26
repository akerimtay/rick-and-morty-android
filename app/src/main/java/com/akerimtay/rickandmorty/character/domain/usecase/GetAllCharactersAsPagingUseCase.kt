package com.akerimtay.rickandmorty.character.domain.usecase

import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import com.akerimtay.rickandmorty.common.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetAllCharactersAsPagingUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : UseCase<Unit, List<Character>>() {
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun execute(parameters: Unit): List<Character> = characterRepository.getAll()
}