package it.wemake.covid19Companion.data.models.response.search

import it.wemake.covid19Companion.data.models.response.CharacterResponse


data class CharacterSearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)

