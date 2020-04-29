package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.CountriesCasesDataDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CasesDataLocal @Inject constructor(
    private val countriesCasesDataDao: CountriesCasesDataDao
): ICasesDataLocal{

    override suspend fun insertCountriesCasesData(countryCasesData: List<NovelCountryCasesDataEntity>) {
        countriesCasesDataDao.insertAreasCasesData(countryCasesData.map {
            it.toLocal()
        })
    }


    override suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDataEntity>> =
        countriesCasesDataDao.getCountriesCasesData(page, pageSize).map { countriesCasesData ->
            countriesCasesData.map {
                it.toEntity()
            }
        }

    override suspend fun getGlobalCasesData(): Flow<GlobalStatsEntity> =
        countriesCasesDataDao.getGlobalCasesData().map {
            it.toEntity()
        }

}