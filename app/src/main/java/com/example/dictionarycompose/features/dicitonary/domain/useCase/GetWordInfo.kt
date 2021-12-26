package com.example.dictionarycompose.features.dicitonary.domain.useCase

import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo
import com.example.dictionarycompose.features.dicitonary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> = if (word.isBlank()) {
        flow {}
    } else {
        repository.getWordInfo(word)
    }
}