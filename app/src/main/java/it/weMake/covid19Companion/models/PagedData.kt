package it.weMake.covid19Companion.models

data class PagedData<Data>(
    val page: Int,
    val data: Data
)