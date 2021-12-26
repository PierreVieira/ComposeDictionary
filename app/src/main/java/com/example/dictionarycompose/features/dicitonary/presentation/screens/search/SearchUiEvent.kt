package com.example.dictionarycompose.features.dicitonary.presentation.screens.search

sealed class SearchUiEvent {
    data class ShowSnackbar(val message: String): SearchUiEvent()
}
