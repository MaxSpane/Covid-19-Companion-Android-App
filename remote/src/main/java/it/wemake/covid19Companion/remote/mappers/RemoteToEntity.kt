package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.casesData.CountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import it.wemake.covid19Companion.data.models.casesData.NigeriaRegionCasesDataEntity
import it.wemake.covid19Companion.data.models.screeningTool.*
import it.wemake.covid19Companion.remote.models.casesData.CountryCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.CountryInfoRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.NigeriaRegionCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.*

internal fun CountryCasesDataRemoteModel.toEntity(): CountryCasesDataEntity =
    CountryCasesDataEntity(
        country,
        updated,
        countryInfo.toEntity(),
        cases,
        deaths,
        recovered,
        todayCases,
        todayDeaths,
        todayRecovered,
        continent,
        casesPerOneMillion,
        deathsPerOneMillion,
        recoveredPerOneMillion,
        false
    )

internal fun CountryInfoRemoteModel.toEntity(): CountryInfoEntity =
    CountryInfoEntity(
        iso2?.let { it } ?: "",
        iso3.let { it } ?: ""
    )

internal fun NextQuestionRemoteModel.toEntity(): NextQuestionEntity =
    NextQuestionEntity(
        should_stop,
        question?.toEntity()
    )

internal fun QuestionRemoteModel.toEntity(): QuestionEntity =
    QuestionEntity(
        explanation,
        text,
        type,
        items.map { it.toEntity() }
    )

internal fun QuestionItemRemoteModel.toEntity(): QuestionItemEntity =
    QuestionItemEntity(
        id,
        name,
        explanation,
        choices.map { it.toEntity() }
    )

internal fun ChoiceRemoteModel.toEntity(): ChoiceEntity =
    ChoiceEntity(
        id,
        label
    )

internal fun DiagnosisRemoteModel.toEntity(): DiagnosisEntity =
    DiagnosisEntity(
        triage_level,
        label,
        description,
        serious.map { it.toEntity() }
    )

internal fun ObservationRemoteModel.toEntity(): ObservationEntity =
    ObservationEntity(
        common_name,
        is_emergency
    )

internal fun NigeriaRegionCasesDataRemoteModel.toEntity(): NigeriaRegionCasesDataEntity =
    NigeriaRegionCasesDataEntity(
        updated,
        state,
        cases,
        recovered,
        deaths
    )