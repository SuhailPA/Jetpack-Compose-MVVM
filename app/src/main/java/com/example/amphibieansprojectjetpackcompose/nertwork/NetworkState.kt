package com.example.amphibieansprojectjetpackcompose.nertwork

import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import kotlinx.coroutines.flow.MutableStateFlow

sealed interface NetworkState {
    data class Success(val data: List<AphibeanItem>, val currentItem: AphibeanItem) : NetworkState
    data class Error(val error : String) : NetworkState
    object Loading : NetworkState

}