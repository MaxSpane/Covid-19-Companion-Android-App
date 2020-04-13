package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import kotlinx.coroutines.flow.Flow

interface IAreasCasesDataLocal {

    suspend fun insertAreasCasesData(areasCasesData: List<AreaCasesDataEntity>)

    suspend fun getAreasCasesData(parentId: String): Flow<List<AreaCasesDataEntity>>

    suspend fun getCountriesCasesData(): Flow<List<CountryCasesDataEntity>>

    suspend fun getGlobalCasesData(): Flow<AreaCasesDataEntity?>

}