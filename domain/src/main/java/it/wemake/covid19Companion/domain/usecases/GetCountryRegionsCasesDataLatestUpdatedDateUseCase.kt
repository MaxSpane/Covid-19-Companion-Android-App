package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Inject

class GetCountryRegionsCasesDataLatestUpdatedDateUseCase @Inject constructor(
    private val covid19CasesRepository: ICovid19CasesRepository
) {

    suspend operator fun invoke(countryName: String) = covid19CasesRepository.getCountryRegionsCasesDataLatestUpdatedDate(countryName)

}