package it.wemake.covid19Companion.data.local

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesLocal {

    suspend fun updateCasesSummaryLastUpdated(lastUpdated: Long)

    suspend fun getCasesSummaryLastUpdatedFlow(): Flow<Long>

    suspend fun getCasesSummaryLastUpdated(): Long

    fun getWHOHandHygieneDownloadId(): Long

    fun setWHOHandHygieneDownloadId(downloadId: Long)

}