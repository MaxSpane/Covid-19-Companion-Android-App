package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.mappers.toRegionDataEntity
import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.domain.models.casesData.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.casesData.GlobalStatsDomainModel
import it.wemake.covid19Companion.domain.models.casesData.RegionCasesDataDomainModel
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Covid19CasesRepository @Inject constructor(
    private val casesDataLocal: ICasesDataLocal,
    private val covid19CasesRemote: ICovid19CasesRemote,
    private val sharedPreferencesLocal: ISharedPreferencesLocal
): ICovid19CasesRepository {

    private val countriesWithRegionalCasesData = listOf("Nigeria", "USA")

    override suspend fun updateCasesData() {
        covid19CasesRemote.getCasesSummary().collect {allData ->
            val localLastUpdated = sharedPreferencesLocal.getCasesSummaryLastUpdated()
            var remoteLastUpdated = localLastUpdated
            val updatedCasesData = ArrayList<CountryCasesDataEntity>()

            for (countryData in allData){
                if (localLastUpdated < countryData.lastUpdated){
                    countryData.hasRegionalCasesData = countriesWithRegionalCasesData.contains(countryData.displayName)
                    updatedCasesData.add(countryData)
                    if (remoteLastUpdated < countryData.lastUpdated)
                        remoteLastUpdated = countryData.lastUpdated
                }
            }

            if (remoteLastUpdated != localLastUpdated){
                casesDataLocal.insertCountriesCasesData(updatedCasesData)
                sharedPreferencesLocal.updateCasesSummaryLastUpdated(remoteLastUpdated)
            }
        }
    }

    override suspend fun getGlobalCasesData(): Flow<GlobalStatsDomainModel> =
        casesDataLocal.getGlobalCasesData().map {
            it.toDomain()
        }

    override suspend fun getPagedCountriesCasesData(page: Int, pageSize: Int, sortBy: String): Flow<List<CountryCasesDomainModel>> =
        casesDataLocal.getPagedCountriesCasesData(page, pageSize, sortBy).map { countriesCasesData->
            countriesCasesData.map {
                it.toDomain()
            }
        }

    override suspend fun searchCountriesCasesData(
        searchQuery: String,
        page: Int,
        pageSize: Int
    ): Flow<List<CountryCasesDomainModel>> =
        casesDataLocal.searchCountriesCasesData(searchQuery, page, pageSize).map { countriesCasesData->
            countriesCasesData.map {
                it.toDomain()
            }
        }

    override suspend fun getUserCountryCasesData(): Flow<CountryCasesDomainModel?> =
        casesDataLocal.getUserCountryCasesData(sharedPreferencesLocal.getUserCountryIso2())
            .map {
                it?.toDomain()
            }

    override suspend fun updateCountryRegionsCasesData(countryName: String) {
        when(countryName){

            "Nigeria" -> updateNigeriaRegionsCasesData()

            "USA" -> updateUSARegionsCasesData()

        }
    }

    private suspend fun updateNigeriaRegionsCasesData(){
        covid19CasesRemote.getNigeriaRegionsCasesDataSummary().collect { nigeriaRegionCasesData ->
            val localLatestUpdatedDate = casesDataLocal.getCountryRegionsCasesDataLatestUpdatedDate("Nigeria") ?: 0
            var remoteLatestUpdatedDate = localLatestUpdatedDate
            val updatedRegionCasesData = ArrayList<RegionCasesDataEntity>()

            for (regionData in nigeriaRegionCasesData){

                if (regionData.updated > localLatestUpdatedDate){
                    updatedRegionCasesData.add(regionData.toRegionDataEntity("Nigeria"))
                    if (regionData.updated > remoteLatestUpdatedDate)
                        remoteLatestUpdatedDate = regionData.updated
                }

            }

            if (remoteLatestUpdatedDate != localLatestUpdatedDate)
                casesDataLocal.insertRegionsCasesData(updatedRegionCasesData)

        }
    }

    private suspend fun updateUSARegionsCasesData(){
        covid19CasesRemote.getUSARegionsCasesDataSummary().collect { usaRegionCasesData ->
            val localLatestUpdatedDate = casesDataLocal.getCountryRegionsCasesDataLatestUpdatedDate("USA") ?: 0
            var remoteLatestUpdatedDate = localLatestUpdatedDate
            val updatedRegionCasesData = ArrayList<RegionCasesDataEntity>()

            for (regionData in usaRegionCasesData){

                if (regionData.updated > localLatestUpdatedDate){
                    updatedRegionCasesData.add(regionData.toRegionDataEntity("USA"))
                    if (regionData.updated > remoteLatestUpdatedDate)
                        remoteLatestUpdatedDate = regionData.updated
                }

            }

            if (remoteLatestUpdatedDate != localLatestUpdatedDate)
                casesDataLocal.insertRegionsCasesData(updatedRegionCasesData)

        }
    }

    override suspend fun getAllCountryRegionsCasesData(
        countryName: String,
        sortBy: String
    ): Flow<List<RegionCasesDataDomainModel>> =
        casesDataLocal.getAllCountryRegionsCasesData(countryName, sortBy)
            .map { regionsCasesData ->
                regionsCasesData.map {
                    it.toDomain()
                }
            }

    override suspend fun getCountryRegionsCasesDataLatestUpdatedDate(countryName: String): Long? =
        casesDataLocal.getCountryRegionsCasesDataLatestUpdatedDate(countryName)

    override suspend fun getAllCountriesCasesData(sortBy: String): Flow<List<CountryCasesDomainModel>> =
        casesDataLocal.getAllCountriesCasesData(sortBy).map { countriesCasesData->
            countriesCasesData.map {
                it.toDomain()
            }
        }

}
