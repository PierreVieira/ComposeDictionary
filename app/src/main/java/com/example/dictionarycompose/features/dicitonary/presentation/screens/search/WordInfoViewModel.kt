package com.example.dictionarycompose.features.dicitonary.presentation.screens.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionarycompose.core.util.Resource
import com.example.dictionarycompose.features.dicitonary.domain.useCase.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {

    private val _searchQuery: MutableState<String> = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state: MutableState<WordInfoState> = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow: MutableSharedFlow<SearchUiEvent> = MutableSharedFlow()
    val eventFlow: SharedFlow<SearchUiEvent> = _eventFlow

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            wordsInfo = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            wordsInfo = emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(SearchUiEvent.ShowSnackbar(result.message ?: "Unknow error"))
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            wordsInfo = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}