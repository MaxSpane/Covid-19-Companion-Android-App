package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.preference.Covid19CompanionSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPreferencesLocal @Inject constructor(
    private val covid19CompanionSharedPreferences: Covid19CompanionSharedPreferences
): ISharedPreferencesLocal {

    override suspend fun updateCasesSummaryLastUpdated(lastUpdated: String) {
        covid19CompanionSharedPreferences.updateCasesSummaryLastUpdated(lastUpdated)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getCasesSummaryLastUpdatedFlow(): Flow<String> =
        covid19CompanionSharedPreferences.getCasesSummaryLastUpdatedFlow()

    override suspend fun getCasesSummaryLastUpdated(): String =
        covid19CompanionSharedPreferences.getCasesSummaryLastUpdated()

}