package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ICasesDataLocal
import it.wemake.covid19Companion.data.local.IPreventionTipsLocal
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.models.PreventionTipEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.mappers.toLocal
import it.wemake.covid19Companion.local.room.dao.CountriesCasesDataDao
import it.wemake.covid19Companion.local.room.dao.PreventionTipsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreventionTipsLocal @Inject constructor(
    private val preventionTipsDao: PreventionTipsDao
): IPreventionTipsLocal{

    override suspend fun getPreventionTips(): Flow<List<PreventionTipEntity>> =
        preventionTipsDao.getAllPreventionTips().map { preventionTips ->
            preventionTips.map {
                it.toEntity()
            }
        }

}