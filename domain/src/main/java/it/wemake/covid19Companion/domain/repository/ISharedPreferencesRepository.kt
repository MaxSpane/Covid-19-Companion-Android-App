package it.wemake.covid19Companion.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesRepository {

    suspend fun getCasesLastUpdated(): Flow<Long>

    fun getWHOHandHygieneBrochureDownloadId(): Long

    fun setWHOHandHygieneDownloadId(downloadId: Long)

    fun getIsFirstLaunch(): Boolean

}