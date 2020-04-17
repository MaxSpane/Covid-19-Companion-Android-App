package it.wemake.covid19Companion.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesRepository {

    suspend fun getCasesLastUpdated(): Flow<String>

    fun getWHOHandHygieneBrochureDownloadId(): Long

    fun setWHOHandHygieneDownloadId(downloadId: Long)

}