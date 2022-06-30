package com.akerimtay.rickandmorty.characters.domain

import com.akerimtay.rickandmorty.characters.data.repository.CharacterRepository
import com.akerimtay.rickandmorty.core.common.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCharactersCountAsFlowUseCase @Inject constructor(
    private val charactersRepository: CharacterRepository
) : FlowUseCase<Unit, Int>() {

    override fun execute(parameters: Unit): Flow<Int> {
        return charactersRepository.charactersCount
    }
}