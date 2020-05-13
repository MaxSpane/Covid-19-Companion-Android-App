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

    override fun getUserCountryIso2(): String =
        sharedPreferencesLocal.getUserCountryIso2()

    override fun setUserCountryIso2(userCountryIso2: String) =
        sharedPreferencesLocal.setUserCountryIso2(userCountryIso2)

    override fun getWashHandsInterval(): Int =
        sharedPreferencesLocal.getWashHandsInterval()

    override fun setWashHandsInterval(interval: Int) =
        sharedPreferencesLocal.setWashHandsInterval(interval)

    override fun getDrinkWaterInterval(): Int =
        sharedPreferencesLocal.getDrinkWaterInterval()

    override fun setDrinkWaterInterval(interval: Int) =
        sharedPreferencesLocal.setDrinkWaterInterval(interval)

}