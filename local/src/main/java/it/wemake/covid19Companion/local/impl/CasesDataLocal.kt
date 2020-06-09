package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.CountriesCasesDataDao
import it.wemake.covid19Companion.local.room.dao.RegionsCasesDataDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CasesDataLocal @Inject constructor(
    private val countriesCasesDataDao: CountriesCasesDataDao,
    private val regionsCasesDataDao: RegionsCasesDataDao
): ICasesDataLocal{

    override suspend fun insertCountriesCasesData(countryCasesData: List<CountryCasesDataEntity>) {
        countriesCasesDataDao.insertAreasCasesData(countryCasesData.map {
            it.toLocal()
        })
    }


    override suspend fun getPagedCountriesCasesData(page: Int, pageSize: Int, sortBy: String): Flow<List<CountryCasesDataEntity>> =
        countriesCasesDataDao.getPagedCountriesCasesDataOrderBy(page, pageSize, sortBy).map { countriesCasesData ->
            countriesCasesData.map {
                it.toEntity()
            }
        }

    override suspend fun getGlobalCasesData(): Flow<GlobalStatsEntity> =
        countriesCasesDataDao.getGlobalCasesData().map {
            it.toEntity()
        }

    override suspend fun searchCountriesCasesData(
        searchQuery: String,
        page: Int,
        pageSize: Int
    ): Flow<List<CountryCasesDataEntity>> =
        countriesCasesDataDao.searchCountriesCasesData(searchQuery, page, pageSize).map { countriesCasesData ->
            countriesCasesData.map {
                it.toEntity()
            }
        }

    override suspend fun getUserCountryCasesData(userCountryIso2: String): Flow<CountryCasesDataEntity?> =
        countriesCasesDataDao.getUserCountryCasesData(userCountryIso2).map { it?.toEntity() }

    override suspend fun getAllCountryRegionsCasesData(
        countryName: String,
        sortBy: String
    ): Flow<List<RegionCasesDataEntity>> =
        regionsCasesDataDao.getCountriesCasesDataOrderBy(countryName, sortBy).map { regionsCasesData ->
            regionsCasesData.map {
                it.toEntity()
            }
        }

    override suspend fun insertRegionsCasesData(regionsCasesData: List<RegionCasesDataEntity>) =
        regionsCasesDataDao.insertRegionsCasesData(regionsCasesData.map {
            it.toLocal()
        })

    override suspend fun getCountryRegionsCasesDataLatestUpdatedDate(countryName: String): Long? =
        regionsCasesDataDao.getCountryLatestUpdatedDate(countryName)

    override suspend fun getAllCountriesCasesData(sortBy: String): Flow<List<CountryCasesDataEntity>> =
        countriesCasesDataDao.getAllCountriesCasesDataOrderBy(sortBy).map { countriesCasesData ->
            countriesCasesData.map {
                it.toEntity()
            }
        }

}