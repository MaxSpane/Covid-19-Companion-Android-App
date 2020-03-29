package it.wemake.covid19Companion.remote.mappers

import it.wemake.covid19Companion.data.models.entity.StarWarsCharacterEntity
import it.wemake.covid19Companion.remote.models.CharacterResponse

internal fun CharacterResponse.toEntity(): StarWarsCharacterEntity {
    return StarWarsCharacterEntity(this.name, this.birthYear, this.height, this.url)
}
