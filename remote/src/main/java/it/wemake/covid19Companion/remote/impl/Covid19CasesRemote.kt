package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.AllAreasCasesDataEntity
import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.remote.api.BingCovid19ApiService
import it.wemake.covid19Companion.remote.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Covid19CasesRemote @Inject constructor(
    private val bingCovid19ApiService: BingCovid19ApiService
): ICovid19CasesRemote {

    override suspend fun getCasesSummary(): Flow<AllAreasCasesDataEntity> =
        flow { emit(bingCovid19ApiService.getCasesData().toEntity()) }

}