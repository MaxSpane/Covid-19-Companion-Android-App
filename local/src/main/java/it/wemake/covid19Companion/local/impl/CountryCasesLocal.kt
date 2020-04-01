package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICountryCasesLocal
import it.wemake.covid19Companion.data.models.CasesStatsEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.CountryCasesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CountryCasesLocal @Inject constructor(
    private val countryCasesDao: CountryCasesDao
): ICountryCasesLocal{

    override suspend fun insertCountries(countries: List<CountryCasesEntity>) {
        countryCasesDao.insertCountries(countries.map {
            it.toLocal()
        })
    }

    override suspend fun getCountries(): Flow<List<CountryCasesEntity>> =
        countryCasesDao.getAllCountries().map {countryCases ->
            countryCases.map{
                it.toEntity()
            }
        }

    override suspend fun getCasesStats(): Flow<CasesStatsEntity> =
        countryCasesDao.getStats().map {
            it.toEntity()
        }

}