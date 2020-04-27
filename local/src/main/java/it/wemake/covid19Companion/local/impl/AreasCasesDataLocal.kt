package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.IAreasCasesDataLocal
import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.AreasCasesDataDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AreasCasesDataLocal @Inject constructor(
    private val areasCasesDataDao: AreasCasesDataDao
): IAreasCasesDataLocal{

    override suspend fun insertAreasCasesData(areasCasesData: List<AreaCasesDataEntity>) {
        areasCasesDataDao.insertAreasCasesData(areasCasesData.map {
            it.toLocal()
        })
    }

    override suspend fun getAreasCasesData(parentId: String): Flow<List<AreaCasesDataEntity>> {
        return areasCasesDataDao.getAreasCasesData(parentId).map { areasCasesData->
            areasCasesData.map {
                it.toEntity()
            }
        }
    }

    override suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDataEntity>> =
        areasCasesDataDao.getCountriesCasesData(page, pageSize).map {countriesCasesData ->
            countriesCasesData.map {
                it.toEntity()
            }
        }

    override suspend fun getGlobalCasesData(): Flow<AreaCasesDataEntity?> =
        areasCasesDataDao.getGlobalCasesData().map {
            it?.toEntity()
        }

}