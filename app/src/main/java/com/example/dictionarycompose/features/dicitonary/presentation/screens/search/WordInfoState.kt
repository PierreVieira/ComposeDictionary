package com.example.dictionarycompose.features.dicitonary.presentation.screens.search

import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo

data class WordInfoState(
    val wordsInfo: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
