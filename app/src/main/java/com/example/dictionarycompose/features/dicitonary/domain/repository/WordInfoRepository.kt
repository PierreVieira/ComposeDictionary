package com.example.dictionarycompose.features.dicitonary.domain.repository

import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}