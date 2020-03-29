package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterFilmEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterPlanetEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterSpeciesEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterEntity
import it.wemake.covid19Companion.domain.models.StarWarsCharacter
import it.wemake.covid19Companion.domain.models.StarWarsCharacterFilm
import it.wemake.covid19Companion.domain.models.StarWarsCharacterPlanet
import it.wemake.covid19Companion.domain.models.StarWarsCharacterSpecies


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

