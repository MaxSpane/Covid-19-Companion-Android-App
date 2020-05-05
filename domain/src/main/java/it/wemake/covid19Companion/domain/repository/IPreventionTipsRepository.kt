package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.preventionTips.PreventionTipDomainModel
import kotlinx.coroutines.flow.Flow

interface IPreventionTipsRepository {

    suspend fun getPreventionTips(): Flow<List<PreventionTipDomainModel>>

}