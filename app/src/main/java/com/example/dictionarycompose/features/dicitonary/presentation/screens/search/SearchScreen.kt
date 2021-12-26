package com.example.dictionarycompose.features.dicitonary.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionarycompose.features.dicitonary.domain.model.WordInfo
import com.example.dictionarycompose.features.dicitonary.presentation.screens.search.components.WordInfoItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    viewModel: WordInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SearchUiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    SearchScreenContent(
        scaffoldState = scaffoldState,
        wordsInfo = state.wordsInfo,
        isLoading = state.isLoading,
        searchText = viewModel.searchQuery.value,
        onSearchValueChange = viewModel::onSearch
    )
}

@Composable
private fun SearchScreenContent(
    scaffoldState: ScaffoldState,
    isLoading: Boolean,
    wordsInfo: List<WordInfo>,
    searchText: String,
    onSearchValueChange: (String) -> Unit
) {
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = searchText,
                        onValueChange = onSearchValueChange,
                        placeholder = {
                            Text(text = "Search...")
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                itemsIndexed(wordsInfo) { index, wordInfo ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    WordInfoItem(wordInfo = wordInfo)
                    if (index < wordsInfo.size - 1) {
                        Divider()
                    }
                }
            }
        }
    }
}
