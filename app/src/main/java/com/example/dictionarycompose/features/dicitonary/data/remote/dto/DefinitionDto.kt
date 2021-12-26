package com.example.dictionarycompose.features.dicitonary.data.remote.dto


import com.example.dictionarycompose.features.dicitonary.data.util.DtoInterface
import com.example.dictionarycompose.features.dicitonary.domain.model.Definition
import com.google.gson.annotations.SerializedName

data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String>,
    @SerializedName("definition")
    val definition: String,
    @SerializedName("example")
    val example: String?,
    @SerializedName("synonyms")
    val synonyms: List<String>
) : DtoInterface<Definition> {
    override fun toDomain(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}