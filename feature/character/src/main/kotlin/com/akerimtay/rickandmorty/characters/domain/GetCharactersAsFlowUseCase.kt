package com.akerimtay.rickandmorty.characters.domain

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.core.common.model.Character
import com.akerimtay.rickandmorty.core.common.model.CharacterStatus
import com.akerimtay.rickandmorty.core.common.model.Gender
import com.akerimtay.rickandmorty.core.common.usecase.FlowUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCharactersAsFlowUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : FlowUseCase<GetCharactersAsFlowUseCase.Param, PagingData<Character>>() {

    override fun execute(parameters: Param): Flow<PagingData<Character>> {
        return characterRepository.getAsPagingData(
            name = parameters.name,
            status = parameters.status,
            gender = parameters.gender
        )
    }

    data class Param(
        val name: String? = null,
        val status: CharacterStatus? = null,
        val gender: Gender? = null
    )
}