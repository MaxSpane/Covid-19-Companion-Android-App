package it.wemake.covid19Companion.remote.models.search

import it.wemake.covid19Companion.remote.models.CharacterResponse


data class CharacterSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)

