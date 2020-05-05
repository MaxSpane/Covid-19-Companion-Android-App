package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.models.screeningTool.requests.ScreeningToolRequestDomainModel
import it.wemake.covid19Companion.domain.repository.IScreeningToolRepository
import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class RequestNextQuestionUseCase @Inject constructor(
    private val screeningToolRepository: IScreeningToolRepository
) {

    suspend operator fun invoke(request: ScreeningToolRequestDomainModel) = screeningToolRepository.requestNextQuestion(request)

}