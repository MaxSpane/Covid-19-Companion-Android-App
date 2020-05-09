package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.preference.Covid19CompanionSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPreferencesLocal @Inject constructor(
    private val covid19CompanionSharedPreferences: Covid19CompanionSharedPreferences
): ISharedPreferencesLocal {

    override suspend fun updateCasesSummaryLastUpdated(lastUpdated: Long) {
        covid19CompanionSharedPreferences.updateCasesSummaryLastUpdated(lastUpdated)
    }

    @ExperimentalCoroutinesApi
    override suspend fun getCasesSummaryLastUpdatedFlow(): Flow<Long> =
        covid19CompanionSharedPreferences.getCasesSummaryLastUpdatedFlow()

    override suspend fun getCasesSummaryLastUpdated(): Long =
        covid19CompanionSharedPreferences.getCasesSummaryLastUpdated()

    override fun getWHOHandHygieneDownloadId(): Long =
        covid19CompanionSharedPreferences.getWHOHandHygieneDownloadId()

    override fun setWHOHandHygieneDownloadId(downloadId: Long) =
        covid19CompanionSharedPreferences.setWHOHandHygieneDownloadId(downloadId)

    override fun getUserCountryIso2(): String =
        covid19CompanionSharedPreferences.getUserCountryIso2()

    override fun setUserCountryIso2(userCountryIso2: String) =
        covid19CompanionSharedPreferences.setUserCountryIso2(userCountryIso2)

}