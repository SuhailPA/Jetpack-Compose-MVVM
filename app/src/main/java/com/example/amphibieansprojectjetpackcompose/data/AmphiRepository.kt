package com.example.amphibieansprojectjetpackcompose.data

import com.example.amphibieansprojectjetpackcompose.model.AphibeanItem
import com.example.amphibieansprojectjetpackcompose.nertwork.AmphibieanAPI

interface AphiRepository {

    suspend fun getAllData() : List<AphibeanItem>
}


class AphiRepositoryImpl(val amphibeanAPI : AmphibieanAPI) : AphiRepository{
    override suspend fun getAllData(): List<AphibeanItem> = amphibeanAPI.getAllData()

}