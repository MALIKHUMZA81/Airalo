package com.airalo.assignment.ui.packages

sealed class PackagesUiState

object LoadingState : PackagesUiState()
object ContentState : PackagesUiState()
class ErrorState(val message: String) : PackagesUiState()
