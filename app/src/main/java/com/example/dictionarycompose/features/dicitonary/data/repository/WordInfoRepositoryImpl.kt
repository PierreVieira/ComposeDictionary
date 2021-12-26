package com.example.dictionarycompose.features.dicitonary.data.repository

import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.features.dicitonary.data.local.WordInfoDao
import com.example.dictionarycompose.features.dicitonary.data.remote.DictionaryApi
import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo
import com.example.dictionarycompose.features.dicitonary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())
        val wordInfos = dao.getWordInfos(word).map { it.toDomain() }
        emit(Resource.Loading(data = wordInfos))
        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = wordInfos
                )
            )
        }
        val newWordInfos = dao.getWordInfos(word).map { it.toDomain() }
        emit(Resource.Success(newWordInfos))
    }
}