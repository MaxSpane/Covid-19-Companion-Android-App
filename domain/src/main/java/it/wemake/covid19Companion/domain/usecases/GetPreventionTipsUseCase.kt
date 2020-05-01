package it.wemake.covid19Companion.domain.usecases

import it.wemake.covid19Companion.domain.repository.IPreventionTipsRepository
import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class GetPreventionTipsUseCase @Inject constructor(
    private val preventionTipsRepository: IPreventionTipsRepository
) {

    suspend operator fun invoke() = preventionTipsRepository.getPreventionTips()

}