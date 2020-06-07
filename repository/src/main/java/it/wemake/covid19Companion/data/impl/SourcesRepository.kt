package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.ISourcesLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.domain.models.sources.SourceDomainModel
import it.wemake.covid19Companion.domain.repository.ISourcesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SourcesRepository @Inject constructor(
    private val sourcesLocal: ISourcesLocal
) : ISourcesRepository{

    override suspend fun getAllSources(): Flow<List<SourceDomainModel>> =
        sourcesLocal.getAllSources().map { it.map { it.toDomain() } }

}