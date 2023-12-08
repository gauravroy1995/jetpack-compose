package com.example.compose

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger
import com.example.compose.data.AppContainer
import com.example.compose.data.DefaultAppContainer

class MarsPhotosApplication : Application(),ImageLoaderFactory {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(context = this)
    }

    override fun newImageLoader(): ImageLoader {

        return ImageLoader(context = this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache{
                MemoryCache.Builder(this)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache{
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
    }
}