package it.wemake.covid19Companion.data.local

import it.wemake.covid19Companion.data.models.developmentTeam.TeamMemberEntity
import kotlinx.coroutines.flow.Flow

interface IDevelopmentTeamLocal {

    suspend fun getTeamMembers(): Flow<List<TeamMemberEntity>>

}