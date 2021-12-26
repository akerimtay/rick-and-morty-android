package com.akerimtay.rickandmorty.character.domain.usecase

import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import com.akerimtay.rickandmorty.common.base.UseCase
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : UseCase<Unit, List<Character>>() {

    override suspend fun execute(parameters: Unit): List<Character> = characterRepository.getAll()
}