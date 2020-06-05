package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.ISourcesRepository
import javax.inject.Inject

class GetAllSourcesUseCase
@Inject constructor(
    private val sourcesRepository: ISourcesRepository
) {

    suspend operator fun invoke() = sourcesRepository.getAllSources()

}