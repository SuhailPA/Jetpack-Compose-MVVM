package com.example.amphibieansprojectjetpackcompose

import android.app.Application
import com.example.amphibieansprojectjetpackcompose.data.AmphibeanContainer
import com.example.amphibieansprojectjetpackcompose.data.AppContainer

class AphiApplication : Application() {

    lateinit var aphiContainter : AppContainer

    override fun onCreate() {
        super.onCreate()
        aphiContainter = AmphibeanContainer()
    }
}