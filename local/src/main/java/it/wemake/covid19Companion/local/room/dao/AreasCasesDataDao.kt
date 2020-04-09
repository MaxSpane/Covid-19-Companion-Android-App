package it.wemake.covid19Companion.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.wemake.covid19Companion.local.models.CasesStatsLocal
import it.wemake.covid19Companion.local.models.AreaCasesDataLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AreasCasesDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAreasCasesData(areaCaseData: List<AreaCasesDataLocalModel>)

    @Query("SELECT * FROM area_cases_data WHERE id = 'world'")
    fun getGlobalCasesData(): Flow<AreaCasesDataLocalModel?>

    @Query("SELECT * FROM area_cases_data WHERE parentId = :parentId")
    fun getAreaCasesData(parentId: String): Flow<List<AreaCasesDataLocalModel>>

}