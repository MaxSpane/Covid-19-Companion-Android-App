package it.wemake.covid19Companion.data.local

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesLocal {

    suspend fun updateCasesSummaryLastUpdated(lastUpdated: String)

    suspend fun getCasesSummaryLastUpdatedFlow(): Flow<String>

    suspend fun getCasesSummaryLastUpdated(): String

    fun getWHOHandHygieneDownloadId(): Long

    fun setWHOHandHygieneDownloadId(downloadId: Long)

}