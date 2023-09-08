package com.example.amphibieansprojectjetpackcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class AphibeanItem(
    val description: String,
    val img_src: String,
    val name: String,
    val type: String
)