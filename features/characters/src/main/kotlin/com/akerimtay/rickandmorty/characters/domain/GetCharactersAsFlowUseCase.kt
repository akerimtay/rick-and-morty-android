package com.akerimtay.rickandmorty.characters.domain

import androidx.paging.PagingData
import com.akerimtay.rickandmorty.characters.data.repository.CharacterRepository
import com.akerimtay.rickandmorty.core.common.usecase.FlowUseCase
import com.akerimtay.rickandmorty.model.Character
import com.akerimtay.rickandmorty.model.CharacterStatus
import com.akerimtay.rickandmorty.model.Gender
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCharactersAsFlowUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) : FlowUseCase<GetCharactersAsFlowUseCase.Param, Flow<PagingData<Character>>>() {

    override fun execute(parameters: Param): Flow<PagingData<Character>> {
        return characterRepository.getAsPagingData(
            pageSize = parameters.pageSize,
            name = parameters.name,
            status = parameters.status,
            gender = parameters.gender
        )
    }

    data class Param(
        val pageSize: Int = DEFAULT_PAGE_SIZE,
        val name: String? = null,
        val status: CharacterStatus? = null,
        val gender: Gender? = null
    )

    companion object {

        private const val DEFAULT_PAGE_SIZE = 20
    }
}