package it.wemake.covid19Companion.data.impl

import it.wemake.covid19Companion.data.local.IDevelopmentTeamLocal
import it.wemake.covid19Companion.data.mappers.toDomain
import it.wemake.covid19Companion.domain.models.developmentTeam.TeamMemberDomainModel
import it.wemake.covid19Companion.domain.repository.IDevelopmentTeamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DevelopmentTeamRepository @Inject constructor(
    private val developmentTeamLocal: IDevelopmentTeamLocal
): IDevelopmentTeamRepository {

    override suspend fun getTeamMembers(): Flow<List<TeamMemberDomainModel>> =
        developmentTeamLocal.getTeamMembers().map { it.map { it.toDomain() } }

}