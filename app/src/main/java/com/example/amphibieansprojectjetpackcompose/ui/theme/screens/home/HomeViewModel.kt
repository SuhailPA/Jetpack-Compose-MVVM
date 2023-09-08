package com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibieansprojectjetpackcompose.AphiApplication
import com.example.amphibieansprojectjetpackcompose.data.AphiRepository
import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import com.example.amphibieansprojectjetpackcompose.nertwork.NetworkState
import kotlinx.coroutines.launch

class HomeViewModel(val repository: AphiRepository) : ViewModel() {

    var networkState: NetworkState by mutableStateOf(NetworkState.Loading)
        private set

    init {
        getAllData()
    }

    private fun getAllData() {
        viewModelScope.launch {
            networkState = try {
                NetworkState.Success(data = repository.getAllData())
            } catch (e: Exception) {
                NetworkState.Error(error = e.message ?: "Null")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AphiApplication)
                val repository = application.aphiContainter.amphiRepository
                HomeViewModel(repository = repository)
            }
        }
    }
}