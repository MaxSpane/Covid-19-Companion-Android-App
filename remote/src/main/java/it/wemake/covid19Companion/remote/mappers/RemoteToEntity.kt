package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.AllAreasCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesDataEntity
import it.wemake.covid19Companion.remote.models.casesData.AllAreasCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.CountryCasesRemoteModel

internal fun AllAreasCasesDataRemoteModel.toEntity(): AllAreasCasesDataEntity =
    AllAreasCasesDataEntity(
        id,
        displayName,
        areas.map {
            it.toEntity()
        },
        lastUpdated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        parentId
    )

