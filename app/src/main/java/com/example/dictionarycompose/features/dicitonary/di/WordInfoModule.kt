package com.example.dictionarycompose.features.dicitonary.di

import android.app.Application
import androidx.room.Room
import com.example.dictionarycompose.features.dicitonary.data.local.Converters
import com.example.dictionarycompose.features.dicitonary.data.local.WordInfoDao
import com.example.dictionarycompose.features.dicitonary.data.local.WordInfoDatabase
import com.example.dictionarycompose.features.dicitonary.data.remote.DictionaryApi
import com.example.dictionarycompose.features.dicitonary.data.repository.WordInfoRepositoryImpl
import com.example.dictionarycompose.features.dicitonary.data.util.GsonParser
import com.example.dictionarycompose.features.dicitonary.domain.repository.WordInfoRepository
import com.example.dictionarycompose.features.dicitonary.domain.useCase.GetWordInfo
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    private const val BASE_URL = "https://api.dictionaryapi.dev/"

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase = Room
        .databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word_db"
        )
        .addTypeConverter(Converters(GsonParser(Gson())))
        .build()

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        database: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository = WordInfoRepositoryImpl(api, database.dao)

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo =
        GetWordInfo(repository)

}