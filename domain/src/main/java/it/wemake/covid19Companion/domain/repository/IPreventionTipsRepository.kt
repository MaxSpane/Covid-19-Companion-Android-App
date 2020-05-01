package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.PreventionTipDomainModel
import kotlinx.coroutines.flow.Flow

interface IPreventionTipsRepository {

    suspend fun getPreventionTips(): Flow<List<PreventionTipDomainModel>>

}