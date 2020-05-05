package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.casesData.NovelCountryCasesDataEntity
import it.wemake.covid19Companion.data.models.CountryInfoEntity
import it.wemake.covid19Companion.data.models.screeningTool.*
import it.wemake.covid19Companion.remote.models.casesData.CountryCasesDataRemoteModel
import it.wemake.covid19Companion.remote.models.casesData.CountryInfoRemoteModel
import it.wemake.covid19Companion.remote.models.screeningTool.*

internal fun CountryCasesDataRemoteModel.toEntity(): NovelCountryCasesDataEntity =
    NovelCountryCasesDataEntity(
        updated,
        country,
        countryInfo.toEntity(),
        cases,
        todayCases,
        deaths,
        todayDeaths,
        recovered,
        continent
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