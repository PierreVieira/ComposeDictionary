package com.example.dictionarycompose.features.dicitonary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionarycompose.features.dicitonary.data.util.JsonParser
import com.example.dictionarycompose.features.dicitonary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> = jsonParser.fromJson<ArrayList<Meaning>>(
        json = json,
        type = object : TypeToken<ArrayList<Meaning>>() {}.type
    ) ?: emptyList()

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String = jsonParser.toJson(
        obj = meanings,
        type = object : TypeToken<ArrayList<Meaning>>() {}.type
    ) ?: "[]"
}