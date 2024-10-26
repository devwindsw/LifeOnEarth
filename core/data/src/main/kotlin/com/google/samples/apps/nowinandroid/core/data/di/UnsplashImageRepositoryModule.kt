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

package com.google.samples.apps.nowinandroid.core.data.di

import com.devwindsw.lifeonearth.core.unsplash.UnsplashNetworkDataSource
import com.google.samples.apps.nowinandroid.core.data.repository.DefaultSharedDataRepository
import com.google.samples.apps.nowinandroid.core.data.repository.ImageRepository
import com.google.samples.apps.nowinandroid.core.data.repository.SharedDataRepository
import com.google.samples.apps.nowinandroid.core.data.repository.UnsplashImageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/*
// Issue#1: error: [Dagger/MissingBinding] UnsplashImageRepository cannot be provided without an @Inject constructor or an @Provides-annotated method.
// First try: Implement @Provides in DataModule
// Issue#2: A @Module may not contain both non-static and abstract binding methods
// Refer to https://hungseong.tistory.com/29
@Module
@InstallIn(SingletonComponent::class)
object UnsplashImageRepositoryModule {
    @Provides
    @Singleton
    fun bindsUnsplashImageRepository(): ImageRepository {
        return UnsplashImageRepository()
    }
}
*/

@Module
@InstallIn(SingletonComponent::class)
abstract class UnsplashImageRepositoryModule {
    @Binds
    internal abstract fun bindsUnsplashImageRepository(
        unsplashImageRepository: UnsplashImageRepository,
    ): ImageRepository
}