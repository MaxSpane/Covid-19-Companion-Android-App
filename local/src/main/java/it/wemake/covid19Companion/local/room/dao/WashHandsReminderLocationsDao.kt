package it.wemake.covid19Companion.local.room.dao

import androidx.room.*
import it.wemake.covid19Companion.local.models.WashHandsReminderLocationLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface WashHandsReminderLocationsDao {

    @Query("SELECT * FROM wash_hands_reminder_locations")
    fun getAllWashHandsReminderLocations(): Flow<List<WashHandsReminderLocationLocalModel>>

    @Insert
    fun insertWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationLocalModel): Long

    @Delete
    fun deleteWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationLocalModel)

    @Update
    fun updateWashHandsReminderLocation(washHandsReminderLocation: WashHandsReminderLocationLocalModel)

    @Query("SELECT * FROM wash_hands_reminder_locations WHERE id = :id")
    fun getWashHandsReminderLocation(id: Int): Flow<WashHandsReminderLocationLocalModel>

}