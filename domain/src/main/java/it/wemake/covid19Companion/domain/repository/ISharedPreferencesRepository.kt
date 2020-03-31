package it.wemake.covid19Companion.domain.repository

import kotlinx.coroutines.flow.Flow

interface ISharedPreferencesRepository {

    suspend fun getNumberOfTries(): Flow<Int>

}