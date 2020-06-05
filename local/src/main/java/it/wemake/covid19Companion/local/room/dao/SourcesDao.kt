package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import it.wemake.covid19Companion.local.models.SourceLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SourcesDao {

    @Query("SELECT * FROM sources")
    fun getAllCountries(): Flow<List<SourceLocalModel>>

}