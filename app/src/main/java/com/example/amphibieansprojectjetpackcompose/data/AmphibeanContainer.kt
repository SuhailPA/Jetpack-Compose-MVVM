package com.example.amphibieansprojectjetpackcompose.data

import com.example.amphibieansprojectjetpackcompose.nertwork.AmphibieanAPI
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
    val amphiRepository: AphiRepository
}

class AmphibeanContainer : AppContainer {

    val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: AmphibieanAPI by lazy {
        retrofit.create(AmphibieanAPI::class.java)
    }

    override val amphiRepository: AphiRepository by lazy {
        AphiRepositoryImpl(retrofitService)
    }

}