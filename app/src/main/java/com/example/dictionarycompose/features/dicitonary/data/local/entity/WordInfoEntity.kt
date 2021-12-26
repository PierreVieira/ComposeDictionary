package com.example.dictionarycompose.features.dicitonary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dictionarycompose.features.dicitonary.data.util.DtoInterface
import com.example.dictionarycompose.features.dicitonary.domain.model.Meaning
import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val phonetic: String,
    val origin: String,
    val meanings: List<Meaning>
) : DtoInterface<WordInfo> {
    override fun toDomain(): WordInfo {
        return WordInfo(
            word = word,
            phonetic = phonetic,
            origin = origin,
            meanings = meanings
        )
    }
}