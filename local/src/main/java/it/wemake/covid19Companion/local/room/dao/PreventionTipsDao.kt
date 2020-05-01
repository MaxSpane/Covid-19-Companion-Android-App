package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CountryLocalModel
import it.wemake.covid19Companion.local.models.PreventionTipLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PreventionTipsDao {

    @Query("SELECT * FROM prevention_tips")
    fun getAllPreventionTips(): Flow<List<PreventionTipLocalModel>>

}