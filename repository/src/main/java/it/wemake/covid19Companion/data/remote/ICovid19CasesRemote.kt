package it.wemake.covid19Companion.data.remote

import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.NigeriaRegionCasesDataEntity
import kotlinx.coroutines.flow.Flow

interface ICovid19CasesRemote {

    suspend fun getCasesSummary(): Flow<List<CountryCasesDataEntity>>

    suspend fun getNigeriaRegionsCasesDataSummary(): Flow<List<NigeriaRegionCasesDataEntity>>

}