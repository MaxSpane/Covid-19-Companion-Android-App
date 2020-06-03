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

    override fun getWashHandsInterval(): Int =
        covid19CompanionSharedPreferences.getWashHandsInterval()

    override fun setWashHandsInterval(interval: Int) =
        covid19CompanionSharedPreferences.setWashHandsInterval(interval)

    override fun getDrinkWaterInterval(): Int =
        covid19CompanionSharedPreferences.getDrinkWaterInterval()

    override fun setDrinkWaterInterval(interval: Int) =
        covid19CompanionSharedPreferences.setDrinkWaterInterval(interval)

    override fun getUseCustomNotificationTone(): Boolean =
        covid19CompanionSharedPreferences.getUseCustomNotificationTone()

    override fun setUseCustomNotificationTone(useCustomNotificationTone: Boolean) =
        covid19CompanionSharedPreferences.setUseCustomNotificationTone(useCustomNotificationTone)

    override fun getRemindUserToWashHandsWhenArrivedAtLocation(): Boolean =
        covid19CompanionSharedPreferences.getRemindUserToWashHandsWhenArrivedAtLocation()

    override fun setRemindUserToWashHandsWhenArrivedAtLocation(
        remindUserToWashHandsWhenArrivedAtLocation: Boolean
    ) =
        covid19CompanionSharedPreferences.setRemindUserToWashHandsWhenArrivedAtLocation(remindUserToWashHandsWhenArrivedAtLocation)

    override fun getDailyMotivation(): String =
        covid19CompanionSharedPreferences.getDailyMotivation()

    override fun setDailyMotivation(dailyMotivation: String) {
        covid19CompanionSharedPreferences.setDailyMotivation(dailyMotivation)
    }

    override fun getLatestVersionCode(): Int =
        covid19CompanionSharedPreferences.getLatestVersionCode()

    override fun setLatestVersionCode(latestVersionCode: Int) {
        covid19CompanionSharedPreferences.setLatestVersionCode(latestVersionCode)
    }

    override fun getAppUpdateDownloadId(): Long =
        covid19CompanionSharedPreferences.getAppUpdateDownloadId()

    override fun setAppUpdateDownloadId(downloadId: Long) {
        covid19CompanionSharedPreferences.setAppUpdateDownloadId(downloadId)
    }

    override fun getUsername(): String =
        covid19CompanionSharedPreferences.getUsername()


    override fun setUsername(username: String) =
        covid19CompanionSharedPreferences.setUsername(username)


}