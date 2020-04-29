package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import kotlinx.coroutines.flow.Flow

interface ICasesDataLocal {

    suspend fun insertCountriesCasesData(countryCasesData: List<NovelCountryCasesDataEntity>)

    suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDataEntity>>

    suspend fun getGlobalCasesData(): Flow<GlobalStatsEntity>

}