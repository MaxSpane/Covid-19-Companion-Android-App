package it.wemake.covid19Companion.remote.models.casesData

import org.jetbrains.annotations.Nullable

data class AllAreasCasesDataRemoteModel(
    val id: String,
    val displayName: String,
    val areas: List<AllAreasCasesDataRemoteModel>,
    val lastUpdated: String,
    @Nullable
    val totalConfirmed: Int?,
    @Nullable
    val totalDeaths: Int?,
    @Nullable
    val totalRecovered: Int?,
    @Nullable
    val totalConfirmedDelta: Int?,
    @Nullable
    val totalDeathsDelta: Int?,
    @Nullable
    val totalRecoveredDelta: Int?,
    @Nullable
    val parentId: String?
)