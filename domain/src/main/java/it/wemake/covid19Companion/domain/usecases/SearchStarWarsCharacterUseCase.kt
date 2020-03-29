package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICharacterSearchRepository
import javax.inject.Inject

class SearchStarWarsCharacterUseCase @Inject constructor(
    private val searchRepository: ICharacterSearchRepository
) {

    suspend operator fun invoke(params: String) = searchRepository.searchCharacters(params)

}