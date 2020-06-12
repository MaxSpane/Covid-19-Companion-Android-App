package it.wemake.covid19Companion.domain.repository

import it.wemake.covid19Companion.domain.models.developmentTeam.TeamMemberDomainModel
import kotlinx.coroutines.flow.Flow

interface IDevelopmentTeamRepository {

    suspend fun getTeamMembers(): Flow<List<TeamMemberDomainModel>>

}