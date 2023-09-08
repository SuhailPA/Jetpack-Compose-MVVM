package com.example.amphibieansprojectjetpackcompose.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home.HomeScreen
import com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(windowSize : WindowWidthSizeClass) {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            val uiState by viewModel.uiState.collectAsState()
            uiState?.let { it1 -> HomeScreen(networkState = viewModel.networkState, windowSize = windowSize, uiState = it1, viewModel = viewModel) }
        }
    }
}