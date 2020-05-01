package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Inject

class GetCountriesCasesDataUseCase @Inject constructor(
    private val covid19CasesRepository: ICovid19CasesRepository
) {

    suspend operator fun invoke(page: Int, pageSize: Int) = covid19CasesRepository.getCountriesCasesData(page, pageSize)

}