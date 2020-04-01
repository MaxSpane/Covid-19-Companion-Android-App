package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.CasesSummaryEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.remote.api.Covid19ApiService
import it.wemake.covid19Companion.remote.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Covid19CasesRemote @Inject constructor(
    private val apiService: Covid19ApiService
): ICovid19CasesRemote {

    override suspend fun getCasesSummary(): Flow<CasesSummaryEntity> =
        flow { emit(apiService.getCasesSummary().toEntity()) }

}