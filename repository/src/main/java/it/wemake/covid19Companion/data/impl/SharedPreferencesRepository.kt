package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val sharedPreferencesLocal: ISharedPreferencesLocal
): ISharedPreferencesRepository {

    override suspend fun getCasesLastUpdated() =
        sharedPreferencesLocal.getCasesSummaryLastUpdatedFlow()

    override fun getWHOHandHygieneBrochureDownloadId(): Long =
        sharedPreferencesLocal.getWHOHandHygieneDownloadId()

    override fun setWHOHandHygieneDownloadId(downloadId: Long) =
        sharedPreferencesLocal.setWHOHandHygieneDownloadId(downloadId)

    override fun getIsFirstLaunch(): Boolean =
        sharedPreferencesLocal.getIsFirstLaunch()
}