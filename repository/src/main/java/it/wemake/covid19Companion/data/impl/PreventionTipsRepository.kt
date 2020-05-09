package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.IPreventionTipsLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.domain.models.preventionTips.PreventionTipDomainModel
import it.wemake.covid19Companion.domain.repository.IPreventionTipsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreventionTipsRepository @Inject constructor(
    private val preventionTipsLocal: IPreventionTipsLocal
): IPreventionTipsRepository {

    override suspend fun getPreventionTips(): Flow<List<PreventionTipDomainModel>> =
        preventionTipsLocal.getPreventionTips().map { preventionTips ->
            preventionTips.map {
                it.toDomain()
            }
        }

}