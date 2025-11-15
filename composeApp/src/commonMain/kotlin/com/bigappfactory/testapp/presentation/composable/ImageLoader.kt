package com.bigappfactory.testapp.presentation.composable

import androidx.compose.runtime.Composable
import coil3.compose.setSingletonImageLoaderFactory
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger

@Composable
fun ImageLoader(block: @Composable () -> Unit) {
    setSingletonImageLoaderFactory { context ->
        coil3.ImageLoader.Builder(context)
            .networkCachePolicy(CachePolicy.ENABLED)
            .memoryCache(
                MemoryCache.Builder()
                    .maxSizeBytes(1024 * 1000 * 100) //100MB
                    .build()
            )
            .crossfade(true)
            .logger(DebugLogger())
            .build()
    }

    block()
}