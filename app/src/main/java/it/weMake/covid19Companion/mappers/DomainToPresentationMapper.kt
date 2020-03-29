package it.weMake.covid19Companion.mappers

import it.weMake.covid19Companion.models.StarWarsCharacterUiModel
import it.wemake.covid19Companion.domain.models.StarWarsCharacter


fun StarWarsCharacter.toPresentation(): StarWarsCharacterUiModel {
    return StarWarsCharacterUiModel(
        this.name,
        this.birthYear,
        this.height,
        this.height,
        this.url
    )
}
