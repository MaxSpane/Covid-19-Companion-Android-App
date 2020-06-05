package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.sources.SourceEntity
import kotlinx.coroutines.flow.Flow

interface ISourcesLocal {

    suspend fun getAllSources(): Flow<List<SourceEntity>>

}