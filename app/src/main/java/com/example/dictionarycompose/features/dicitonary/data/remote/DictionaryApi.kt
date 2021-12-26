package com.example.dictionarycompose.features.dicitonary.data.remote

import com.example.dictionarycompose.features.dicitonary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>
}