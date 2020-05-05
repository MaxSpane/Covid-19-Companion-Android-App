package it.weMake.covid19Companion.commons


sealed class UiState

object Loading : UiState()
object Success : UiState()
class Error(val error: Throwable) : UiState()
object Resting : UiState()
