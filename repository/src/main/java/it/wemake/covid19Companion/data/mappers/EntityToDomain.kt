package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.*
import it.wemake.covid19Companion.domain.models.*


internal fun StarWarsCharacterEntity.toDomain(): StarWarsCharacter {
    return StarWarsCharacter(
        this.name,
        this.birthYear,
        this.height,
        this.url
    )
}

internal fun StarWarsCharacterFilmEntity.toDomain(): StarWarsCharacterFilm =
    StarWarsCharacterFilm(
        this.title,
        this.openingCrawl
    )

internal fun StarWarsCharacterPlanetEntity.toDomain(): StarWarsCharacterPlanet =
    StarWarsCharacterPlanet(
        this.name,
        this.population
    )

internal fun StarWarsCharacterSpeciesEntity.toDomain(): StarWarsCharacterSpecies =
    StarWarsCharacterSpecies(
        this.name,
        this.language
    )

internal fun CountryEntityModel.toDomain(): CountryDomainModel =
    CountryDomainModel(
        this.slug,
        this.country
    )

