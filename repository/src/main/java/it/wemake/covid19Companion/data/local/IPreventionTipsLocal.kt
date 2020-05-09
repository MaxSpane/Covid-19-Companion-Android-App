package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.preventionTips.PreventionTipEntity
import kotlinx.coroutines.flow.Flow

interface IPreventionTipsLocal {

    suspend fun getPreventionTips(): Flow<List<PreventionTipEntity>>

}