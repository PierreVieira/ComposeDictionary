package com.example.dictionarycompose.features.dicitonary.data.remote.dto


import com.example.dictionarycompose.features.dicitonary.data.local.entity.WordInfoEntity
import com.google.gson.annotations.SerializedName

data class WordInfoDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>,
    @SerializedName("origin")
    val origin: String,
    @SerializedName("phonetic")
    val phonetic: String,
    @SerializedName("word")
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toDomain() },
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }
}