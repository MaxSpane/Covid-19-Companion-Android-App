package it.wemake.covid19Companion.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesRepository {

    suspend fun getCasesLastUpdated(): Flow<Long>

    fun getWHOHandHygieneBrochureDownloadId(): Long

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

    fun getUsername(): String

    fun setUsername(username: String)

    fun getHasLongPressedSplashscreen(): Boolean

    fun setHasLongPressedSplashscreen(hasLongPressedSplashscreen: Boolean)

}