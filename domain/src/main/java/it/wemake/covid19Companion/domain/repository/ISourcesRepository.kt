package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.sources.SourceDomainModel
import kotlinx.coroutines.flow.Flow

interface ISourcesRepository {

    suspend fun getAllSources(): Flow<List<SourceDomainModel>>

}