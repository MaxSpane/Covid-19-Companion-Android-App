package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CountryLocalModel
import it.wemake.covid19Companion.local.models.TeamMemberLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface DevelopmentTeamDao {

    @Query("SELECT * FROM development_team")
    fun getAllTeamMembers(): Flow<List<TeamMemberLocalModel>>

}