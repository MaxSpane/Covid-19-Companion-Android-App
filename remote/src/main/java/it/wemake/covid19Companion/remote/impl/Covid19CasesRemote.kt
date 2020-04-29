package it.wemake.covid19Companion.remote.impl

import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.remote.api.NovelCovidApiService
import it.wemake.covid19Companion.remote.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Covid19CasesRemote @Inject constructor(
    private val novelCovidApiService: NovelCovidApiService
): ICovid19CasesRemote {

    override suspend fun getCasesSummary(): Flow<List<NovelCountryCasesDataEntity>> =
        flow { emit(novelCovidApiService.getCountriesCasesData().map { it.toEntity() }) }

}