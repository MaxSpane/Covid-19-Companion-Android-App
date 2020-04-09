package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.AllAreasCasesDataEntity
import it.wemake.covid19Companion.data.models.AreaCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryCasesEntity
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

internal fun CountryCasesRemoteModel.toEntity(): CountryCasesEntity =
    CountryCasesEntity(
        this.Country,
        this.Slug,
        this.NewConfirmed,
        this.TotalConfirmed,
        this.NewDeaths,
        this.TotalDeaths,
        this.NewRecovered,
        this.TotalRecovered
    )
