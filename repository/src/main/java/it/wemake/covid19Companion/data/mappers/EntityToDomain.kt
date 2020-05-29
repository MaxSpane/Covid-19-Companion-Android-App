package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.*
import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.casesData.GlobalStatsEntity
import it.wemake.covid19Companion.data.models.casesData.RegionCasesDataEntity
import it.wemake.covid19Companion.data.models.preventionTips.PreventionTipEntity
import it.wemake.covid19Companion.data.models.screeningTool.*
import it.wemake.covid19Companion.domain.models.*
import it.wemake.covid19Companion.domain.models.casesData.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.casesData.GlobalStatsDomainModel
import it.wemake.covid19Companion.domain.models.casesData.RegionCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.preventionTips.PreventionTipDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.*


internal fun CountryCasesDataEntity.toDomain(): CountryCasesDomainModel =
    CountryCasesDomainModel(
        displayName,
        lastUpdated,
        countryInfo.toDomain(),
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        totalConfirmedDelta,
        totalDeathsDelta,
        totalRecoveredDelta,
        continent,
        casesPerOneMillion,
        deathsPerOneMillion,
        recoveredPerOneMillion,
        hasRegionalCasesData
    )

internal fun CountryEntityModel.toDomain(): CountryDomainModel =
    CountryDomainModel(
        name,
        iso2
    )

internal fun CountryInfoEntity.toDomain(): CountryInfoDomainModel =
    CountryInfoDomainModel(
        iso2,
        iso3
    )

internal fun GlobalStatsEntity.toDomain(): GlobalStatsDomainModel =
    GlobalStatsDomainModel(
        confirmed,
        recovered,
        deaths
    )

internal fun PreventionTipEntity.toDomain(): PreventionTipDomainModel =
    PreventionTipDomainModel(
        title,
        preventionTip,
        preventionTipWhy,
        iconId
    )

internal fun NextQuestionEntity.toDomain(): NextQuestionDomainModel =
    NextQuestionDomainModel(
        shouldStop,
        question?.toDomain()
    )

internal fun QuestionEntity.toDomain(): QuestionDomainModel =
    QuestionDomainModel(
        explanation,
        text,
        type,
        items.map { it.toDomain() }
    )

internal fun QuestionItemEntity.toDomain(): QuestionItemDomainModel =
    QuestionItemDomainModel(
        id,
        name,
        explanation,
        choices.map { it.toDomain() }
    )

internal fun ChoiceEntity.toDomain(): ChoiceDomainModel =
    ChoiceDomainModel(
        id,
        label
    )

internal fun DiagnosisEntity.toDomain(): DiagnosisDomainModel =
    DiagnosisDomainModel(
        diagnosisLevel,
        label,
        description,
        observations.map { it.toDomain() }
    )

internal fun ObservationEntity.toDomain(): ObservationDomainModel =
    ObservationDomainModel(
        text,
        isEmergency
    )

internal fun RegionCasesDataEntity.toDomain(): RegionCasesDataDomainModel =
    RegionCasesDataDomainModel(
        displayName,
        updated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        parentCountryName
    )