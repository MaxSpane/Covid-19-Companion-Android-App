package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.*
import it.weMake.covid19Companion.models.appReleases.AppRelease
import it.weMake.covid19Companion.models.casesData.CountryCasesData
import it.weMake.covid19Companion.models.casesData.GlobalStats
import it.weMake.covid19Companion.models.casesData.RegionCasesData
import it.weMake.covid19Companion.models.preventionTips.PreventionTip
import it.weMake.covid19Companion.models.screeningTool.*
import it.weMake.covid19Companion.models.washHandsReminderLocations.WashHandsReminderLocation
import it.wemake.covid19Companion.domain.models.*
import it.wemake.covid19Companion.domain.models.appReleases.AppReleaseDomainModel
import it.wemake.covid19Companion.domain.models.casesData.CountryCasesDomainModel
import it.wemake.covid19Companion.domain.models.casesData.GlobalStatsDomainModel
import it.wemake.covid19Companion.domain.models.casesData.RegionCasesDataDomainModel
import it.wemake.covid19Companion.domain.models.preventionTips.PreventionTipDomainModel
import it.wemake.covid19Companion.domain.models.screeningTool.*
import it.wemake.covid19Companion.domain.models.washHandsReminderLocations.WashHandsReminderLocationDomainModel

fun CountryCasesDomainModel.toPresentation(): CountryCasesData =
    CountryCasesData(
        displayName,
        lastUpdated,
        countryInfo.toPresentation(),
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

fun CountryInfoDomainModel.toPresentation(): CountryInfo =
    CountryInfo(
        iso2,
        iso3
    )

fun GlobalStatsDomainModel.toPresentation(): GlobalStats =
    GlobalStats(
        confirmed,
        recovered,
        deaths
    )

fun PreventionTipDomainModel.toPresentation(): PreventionTip =
    PreventionTip(
        title,
        preventionTip,
        preventionTipWhy,
        iconId
    )

fun NextQuestionDomainModel.toPresentation(): NextQuestion =
    NextQuestion(
        shouldStop,
        question?.toPresentation()
    )

fun QuestionDomainModel.toPresentation(): Question =
    Question(
        explanation,
        text,
        type,
        items.map { it.toPresentation() }
    )

fun QuestionItemDomainModel.toPresentation(): QuestionItem =
    QuestionItem(
        id,
        name,
        explanation,
        choices.map { it.toPresentation() }
    )

fun ChoiceDomainModel.toPresentation(): Choice =
    Choice(
        id,
        label
    )

fun DiagnosisDomainModel.toPresentation(): Diagnosis =
    Diagnosis(
        diagnosisLevel,
        label,
        description,
        observations.map { it.toPresentation() }
    )

fun ObservationDomainModel.toPresentation(): Observation =
    Observation(
        text,
        isEmergency
    )

fun CountryDomainModel.toPresentation(): Country =
    Country(
        name,
        iso2
    )

fun WashHandsReminderLocationDomainModel.toPresentation(): WashHandsReminderLocation =
    WashHandsReminderLocation(
        id,
        name,
        address,
        lat,
        lng,
        enabled
    )

fun RegionCasesDataDomainModel.toPresentation(): RegionCasesData =
    RegionCasesData(
        displayName,
        updated,
        totalConfirmed,
        totalDeaths,
        totalRecovered,
        parentCountryName
    )

fun AppReleaseDomainModel.toPresentation(): AppRelease =
    AppRelease(
        versionName,
        versionDetails
    )