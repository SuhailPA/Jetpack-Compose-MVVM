package com.example.amphibieansprojectjetpackcompose.nertwork

import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import retrofit2.http.GET

interface AmphibieanAPI {

    @GET("amphibians")
    suspend fun getAllData() : List<AphibeanItem>

}