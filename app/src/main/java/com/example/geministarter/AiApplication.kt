package com.example.geministarter


import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.geministarter.repository.BusAppContainer
import com.example.geministarter.repository.BusAppDataContainer

class FlightApplication : Application(), ImageLoaderFactory {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: BusAppContainer

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(this).newBuilder().memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
            MemoryCache.Builder(this)
                .maxSizePercent(0.1)
                .strongReferencesEnabled(true)
                .build()
        }.diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder().maxSizePercent(0.03).directory(cacheDir)
                    .build()
            }.logger(DebugLogger()).
            build()
    }

    override fun onCreate() {
        super.onCreate()
        container = BusAppDataContainer(this)
    }
}