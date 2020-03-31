package it.wemake.covid19Companion.data.local

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesLocal {

    suspend fun getNumberOfTries(): Flow<Int>

}