package com.example.amphibieansprojectjetpackcompose.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home.HomeScreen
import com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
            HomeScreen(networkState = viewModel.networkState)
        }
    }
}