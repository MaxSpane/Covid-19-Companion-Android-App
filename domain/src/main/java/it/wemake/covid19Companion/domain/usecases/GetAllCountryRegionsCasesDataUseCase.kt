package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Inject

class GetAllCountryRegionsCasesDataUseCase @Inject constructor(
    private val covid19CasesRepository: ICovid19CasesRepository
) {

    suspend operator fun invoke(countryName: String, sortBy: String) =
        covid19CasesRepository.getAllCountryRegionsCasesData(countryName, sortBy)

}