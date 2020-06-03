package it.wemake.covid19Companion.data.local

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesLocal {

    suspend fun updateCasesSummaryLastUpdated(lastUpdated: Long)

    suspend fun getCasesSummaryLastUpdatedFlow(): Flow<Long>

    suspend fun getCasesSummaryLastUpdated(): Long

    fun getWHOHandHygieneDownloadId(): Long

    fun setWHOHandHygieneDownloadId(downloadId: Long)

    fun getUserCountryIso2(): String

    fun setUserCountryIso2(userCountryIso2: String)

    fun getWashHandsInterval(): Int

    fun setWashHandsInterval(interval: Int)

    fun getDrinkWaterInterval(): Int

    fun setDrinkWaterInterval(interval: Int)

    fun getUseCustomNotificationTone(): Boolean

    fun setUseCustomNotificationTone(useCustomNotificationTone: Boolean)

    fun getRemindUserToWashHandsWhenArrivedAtLocation(): Boolean

    fun setRemindUserToWashHandsWhenArrivedAtLocation(remindUserToWashHandsWhenArrivedAtLocation: Boolean)

    fun getDailyMotivation(): String

    fun setDailyMotivation(dailyMotivation: String)

    fun getLatestVersionCode(): Int

    fun setLatestVersionCode(latestVersionCode: Int)

    fun getAppUpdateDownloadId(): Long

    fun setAppUpdateDownloadId(downloadId: Long)

}