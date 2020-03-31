package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.local.preference.Covid19CompanionSharedPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPreferencesLocal @Inject constructor(
    private val covid19CompanionSharedPreferences: Covid19CompanionSharedPreferences
): ISharedPreferencesLocal {

    override suspend fun getNumberOfTries(): Flow<Int> =
        covid19CompanionSharedPreferences.getNumberOfCheck()

}