package com.akerimtay.rickandmorty.character.domain.usecase

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.character.domain.model.Character
import com.akerimtay.rickandmorty.character.domain.model.CharacterStatus
import com.akerimtay.rickandmorty.character.domain.model.Gender
import com.akerimtay.rickandmorty.character.domain.repository.CharacterRepository
import com.akerimtay.rickandmorty.core.common.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllCharactersAsFlowUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : FlowUseCase<GetAllCharactersAsFlowUseCase.Param, Flow<PagingData<Character>>>() {

    override fun execute(parameters: Param): Flow<PagingData<Character>> =
        characterRepository.getCharactersPagingData(
            name = parameters.name,
            status = parameters.status,
            gender = parameters.gender
        )

    data class Param(
        val name: String? = null,
        val status: CharacterStatus? = null,
        val gender: Gender? = null
    )
}