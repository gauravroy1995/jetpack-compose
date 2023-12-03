package com.example.busschedule






import android.app.Application

import com.example.busschedule.container.BusAppContainer
import com.example.busschedule.container.BusAppDataContainer


class BusApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container:BusAppContainer

    override fun onCreate() {
        super.onCreate()
        container = BusAppDataContainer(this)
    }
}
