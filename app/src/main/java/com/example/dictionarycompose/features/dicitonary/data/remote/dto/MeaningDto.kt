package com.example.dictionarycompose.features.dicitonary.data.remote.dto


import com.example.dictionarycompose.features.dicitonary.data.util.DtoInterface
import com.example.dictionarycompose.features.dicitonary.domain.model.Meaning
import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("definitions")
    val definitions: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String
): DtoInterface<Meaning> {
    override fun toDomain(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDomain() },
            partOfSpeech = partOfSpeech
        )
    }
}