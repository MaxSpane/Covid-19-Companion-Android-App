package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import javax.inject.Inject

class GetAreasCasesDataUseCase @Inject constructor(
    private val covid19CasesRepository: ICovid19CasesRepository
) {

    suspend operator fun invoke(parentId: String) = covid19CasesRepository.getAreasCasesData(parentId)

}