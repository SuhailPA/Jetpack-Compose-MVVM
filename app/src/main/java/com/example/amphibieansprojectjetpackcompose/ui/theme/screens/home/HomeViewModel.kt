package com.example.amphibieansprojectjetpackcompose.ui.theme.screens.home

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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(val repository: AphiRepository) : ViewModel() {

    var networkState = MutableStateFlow<NetworkState>(NetworkState.Loading)
        private set

    init {
        getAllData()
    }

    fun updateCurrentItem(selectedItem: AphibeanItem) {
        val currentData = networkState.value
        if (currentData is NetworkState.Success){
            networkState.value = NetworkState.Success(
                data = currentData.data,
                currentItem = selectedItem
            )
        }
    }

    private fun getAllData() {
        viewModelScope.launch {
            networkState.value = try {
                val newList = repository.getAllData()
                NetworkState.Success(data = newList, currentItem = newList[0])
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