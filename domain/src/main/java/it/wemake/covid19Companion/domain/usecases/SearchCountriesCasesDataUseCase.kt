package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Inject

class SearchCountriesCasesDataUseCase @Inject constructor(
    private val covid19CasesRepository: ICovid19CasesRepository
) {

    suspend operator fun invoke(searchQuery: String, page: Int, pageSize: Int) =
        covid19CasesRepository.searchCountriesCasesData(searchQuery, page, pageSize)

}