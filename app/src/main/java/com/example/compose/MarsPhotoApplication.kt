package com.example.compose

import android.app.Application
import com.example.compose.data.AppContainer
import com.example.compose.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}