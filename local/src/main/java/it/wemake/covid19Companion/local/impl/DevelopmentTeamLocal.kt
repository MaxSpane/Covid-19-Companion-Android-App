package it.wemake.covid19Companion.local.impl

import it.wemake.covid19Companion.data.local.IDevelopmentTeamLocal
import it.wemake.covid19Companion.data.models.developmentTeam.TeamMemberEntity
import it.wemake.covid19Companion.local.mappers.toEntity
import it.wemake.covid19Companion.local.room.dao.DevelopmentTeamDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DevelopmentTeamLocal @Inject constructor(
    private val developmentTeamDao: DevelopmentTeamDao
): IDevelopmentTeamLocal {

    override suspend fun getTeamMembers(): Flow<List<TeamMemberEntity>> =
        developmentTeamDao.getAllTeamMembers().map { it.map { it.toEntity() } }

}