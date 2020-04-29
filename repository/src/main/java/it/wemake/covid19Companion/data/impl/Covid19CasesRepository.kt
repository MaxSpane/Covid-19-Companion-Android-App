package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.models.AllAreasCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.GlobalStatsDomainModel
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

    override suspend fun updateCasesData() {
        covid19CasesRemote.getCasesSummary().collect {allData ->
            var localLastUpdated = sharedPreferencesLocal.getCasesSummaryLastUpdated()
            var remoteLastUpdated = localLastUpdated
            val updatedCasesData = ArrayList<NovelCountryCasesDataEntity>()

            for (countryData in allData){
                if (localLastUpdated < countryData.updated){
                    updatedCasesData.add(countryData)
                    if (remoteLastUpdated < countryData.updated)
                        remoteLastUpdated = countryData.updated
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

    override suspend fun getCountriesCasesData(page: Int, pageSize: Int): Flow<List<CountryCasesDomainModel>> =
        casesDataLocal.getCountriesCasesData(page, pageSize).map { countriesCasesData->
            countriesCasesData.map {
                it.toDomain()
            }
        }

}
