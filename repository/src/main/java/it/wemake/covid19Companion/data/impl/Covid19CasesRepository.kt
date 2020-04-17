package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.IAreasCasesDataLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.models.AllAreasCasesDataEntity
import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.domain.models.AreaCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Covid19CasesRepository @Inject constructor(
    private val areasCasesDataLocal: IAreasCasesDataLocal,
    private val covid19CasesRemote: ICovid19CasesRemote,
    private val sharedPreferencesLocal: ISharedPreferencesLocal
): ICovid19CasesRepository {

    override suspend fun updateCasesData() {
        covid19CasesRemote.getCasesSummary().collect {allData ->
            if (allData.lastUpdated != sharedPreferencesLocal.getCasesSummaryLastUpdated()){

                val allAreasCasesData: ArrayList<AreaCasesDataEntity> = ArrayList()

                allAreasCasesData.add(allData.toAreaCasesDataEntity())

                allAreasCasesData.addAll(allData.areas.map {
                    if (it.areas.isNotEmpty())
                        allAreasCasesData.addAll(it.areas.map {subAreas ->
                            subAreas.toAreaCasesDataEntity()
                        })
                    it.toAreaCasesDataEntity()
                })

                areasCasesDataLocal.insertAreasCasesData(allAreasCasesData)
                sharedPreferencesLocal.updateCasesSummaryLastUpdated(allData.lastUpdated!!)
            }
        }
    }

    override suspend fun getAreasCasesData(parentId: String): Flow<List<AreaCasesDataDomainModel>> =
        areasCasesDataLocal.getAreasCasesData(parentId).map { areaCasesData ->
            areaCasesData.map {
                it.toDomain()
            }
        }

    override suspend fun getGlobalCasesData(): Flow<AreaCasesDataDomainModel?> =
        areasCasesDataLocal.getGlobalCasesData().map {
            it?.toDomain()
        }

    override suspend fun getCountriesCasesData(): Flow<List<CountryCasesDomainModel>> =
        areasCasesDataLocal.getCountriesCasesData().map { countriesCasesData->
            countriesCasesData.map {
                it.toDomain()
            }
        }

}

internal fun AllAreasCasesDataEntity.toAreaCasesDataEntity(): AreaCasesDataEntity =
    AreaCasesDataEntity(
        id,
        displayName,
        lastUpdated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        parentId,
        areas.isNotEmpty()
    )