package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ICountryCasesLocal
import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.data.remote.ICovid19CasesRemote
import it.wemake.covid19Companion.domain.models.CasesStatsDomain
import it.wemake.covid19Companion.domain.models.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.repository.ICovid19CasesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Covid19CasesRepository @Inject constructor(
    private val countryCasesLocal: ICountryCasesLocal,
    private val covid19CasesRemote: ICovid19CasesRemote,
    private val sharedPreferencesLocal: ISharedPreferencesLocal
): ICovid19CasesRepository {

    override suspend fun updateCasesSummary() {
        covid19CasesRemote.getCasesSummary().collect {
            if (it.date != sharedPreferencesLocal.getCasesSummaryLastUpdated()){
                countryCasesLocal.insertCountries(it.countries)
                sharedPreferencesLocal.updateCasesSummaryLastUpdated(it.date)
            }
        }
    }

    override suspend fun getCountriesCasesSummary(): Flow<List<CountryCasesDomainModel>> =
        countryCasesLocal.getCountries().map { countries ->
            countries.map {
                it.toDomain()
            }
        }

    override suspend fun getCasesStats(): Flow<CasesStatsDomain> =
        countryCasesLocal.getCasesStats().map {
            it.toDomain()
        }
}