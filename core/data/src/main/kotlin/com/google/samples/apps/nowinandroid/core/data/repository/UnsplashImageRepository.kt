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

package com.google.samples.apps.nowinandroid.core.data.repository

import com.devwindsw.lifeonearth.core.unsplash.UnsplashNetworkDataSource
import com.devwindsw.lifeonearth.core.unsplash.model.asExternalModel
import com.google.samples.apps.nowinandroid.core.model.data.ImageResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

internal class UnsplashImageRepository @Inject constructor(
    private val unsplashNetwork: UnsplashNetworkDataSource,
) : ImageRepository {
    override fun getImageResources(): Flow<List<ImageResource>> = flow {
        emit(unsplashNetwork.getPhotos().map { it.asExternalModel() })
        // Emulate 5 seconds of network delay
        /*delay(5.seconds.inWholeMilliseconds)
        var images: List<ImageResource> = listOf()
        emit(images)*/
    }
}