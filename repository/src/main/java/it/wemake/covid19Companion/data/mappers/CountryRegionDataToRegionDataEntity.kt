package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.casesData.NigeriaRegionCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.USARegionCasesDataEntity

internal fun NigeriaRegionCasesDataEntity.toRegionDataEntity(parentCountryName: String): RegionCasesDataEntity =
    RegionCasesDataEntity(
        state,
        updated,
        cases,
        deaths,
        recovered,
        parentCountryName
    )

internal fun USARegionCasesDataEntity.toRegionDataEntity(parentCountryName: String): RegionCasesDataEntity =
    RegionCasesDataEntity(
        state,
        updated,
        cases,
        deaths,
        recovered,
        parentCountryName
    )