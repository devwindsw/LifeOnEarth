/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devwindsw.lifeonearth.core.unsplash.demo

import JvmUnitTestDemoUnsplashAssetManager
import com.devwindsw.lifeonearth.core.unsplash.UnsplashNetworkDataSource
import com.devwindsw.lifeonearth.core.unsplash.model.UnsplashNetworkPhoto
import com.google.samples.apps.nowinandroid.core.network.Dispatcher
import com.google.samples.apps.nowinandroid.core.network.NiaDispatchers.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

/**
 * [UnsplashNetworkDataSource] implementation that provides static news resources to aid development
 */
class DemoUnsplashNetworkDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoUnsplashAssetManager = JvmUnitTestDemoUnsplashAssetManager,
) : UnsplashNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getPhotos(): List<UnsplashNetworkPhoto> =
        withContext(ioDispatcher) {
            assets.open(PHOTOS_ASSET).use(networkJson::decodeFromStream)
        }

    companion object {
        private const val PHOTOS_ASSET = "photos.json"
    }
}
