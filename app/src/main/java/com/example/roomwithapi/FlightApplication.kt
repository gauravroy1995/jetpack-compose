package com.example.roomwithapi

import android.app.Application
import com.example.roomwithapi.container.BusAppContainer
import com.example.roomwithapi.container.BusAppDataContainer

class FlightApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: BusAppContainer

    override fun onCreate() {
        super.onCreate()
        container = BusAppDataContainer(this)
    }
}