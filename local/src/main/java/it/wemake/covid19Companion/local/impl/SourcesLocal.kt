package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.ISourcesLocal
import it.wemake.covid19Companion.data.models.sources.SourceEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.room.dao.SourcesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SourcesLocal @Inject constructor(
    private val sourcesDao: SourcesDao
): ISourcesLocal {

    override suspend fun getAllSources(): Flow<List<SourceEntity>> =
        sourcesDao.getAllCountries().map { it.map { it.toEntity() } }

}