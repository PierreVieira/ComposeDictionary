package com.example.dictionarycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.dictionarycompose.features.dicitonary.presentation.screens.search.SearchScreen
import com.example.dictionarycompose.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DictionaryTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SearchScreen()
                }
            }
        }
    }
}
