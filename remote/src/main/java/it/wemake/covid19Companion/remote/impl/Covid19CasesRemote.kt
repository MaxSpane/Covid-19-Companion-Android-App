package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.NigeriaRegionCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.USARegionCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.remote.api.NovelCovidApiService
import it.wemake.covid19Companion.remote.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Covid19CasesRemote @Inject constructor(
    private val novelCovidApiService: NovelCovidApiService
): ICovid19CasesRemote {

    override suspend fun getCasesSummary(): Flow<List<CountryCasesDataEntity>> =
        flow { emit(novelCovidApiService.getCountriesCasesData().map { it.toEntity() }) }

    override suspend fun getNigeriaRegionsCasesDataSummary(): Flow<List<NigeriaRegionCasesDataEntity>> =
        flow { emit(novelCovidApiService.getNigeriaRegionsCasesData().map { it.toEntity() }) }

    override suspend fun getUSARegionsCasesDataSummary(): Flow<List<USARegionCasesDataEntity>> =
        flow { emit(novelCovidApiService.getUSARegionsCasesData().map { it.toEntity() }) }

}