package it.wemake.covid19Companion.data.remote

import it.wemake.covid19Companion.data.models.CasesSummaryEntity
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRemote {

    suspend fun getCasesSummary(): Flow<CasesSummaryEntity>

}