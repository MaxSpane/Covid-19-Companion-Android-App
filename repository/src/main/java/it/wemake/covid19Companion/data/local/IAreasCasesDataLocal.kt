package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import kotlinx.coroutines.flow.Flow

interface IAreasCasesDataLocal {

    suspend fun insertAreasCasesData(areasCasesData: List<AreaCasesDataEntity>)

    suspend fun getAreaCasesData(parentId: String): Flow<List<AreaCasesDataEntity>>

    suspend fun getGlobalCasesData(): Flow<AreaCasesDataEntity?>

}