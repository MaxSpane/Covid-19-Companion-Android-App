package it.wemake.covid19Companion.data.mappers

import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterFilmEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterPlanetEntity
import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterSpeciesEntity
import it.wemake.covid19Companion.data.models.response.CharacterResponse

internal fun CharacterResponse.toEntity(): StarWarsCharacterEntity {
    return StarWarsCharacterEntity(this.name, this.birthYear, this.height, this.url)
}
