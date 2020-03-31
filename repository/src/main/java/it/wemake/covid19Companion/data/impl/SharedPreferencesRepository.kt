package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ISharedPreferencesLocal
import it.wemake.covid19Companion.domain.repository.ISharedPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val sharedPreferencesLocal: ISharedPreferencesLocal
): ISharedPreferencesRepository {

    override suspend fun getNumberOfTries(): Flow<Int> =
        sharedPreferencesLocal.getNumberOfTries()

}