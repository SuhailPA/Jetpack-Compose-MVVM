package com.example.amphibieansprojectjetpackcompose.nertwork

import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem

sealed interface NetworkState {
    data class Success(val data: List<AphibeanItem>) : NetworkState
    data class Error(val error : String) : NetworkState
    object Loading : NetworkState

}