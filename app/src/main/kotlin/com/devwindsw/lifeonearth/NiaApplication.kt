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

package com.devwindsw.lifeonearth

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.devwindsw.lifeonearth.core.secrets.CustSecrets
import com.google.samples.apps.nowinandroid.sync.initializers.Sync
import com.devwindsw.lifeonearth.util.ProfileVerifierLogger
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * [Application] class for NiA
 */
@HiltAndroidApp
class NiaApplication : Application(), ImageLoaderFactory {
    @Inject
    lateinit var imageLoader: dagger.Lazy<ImageLoader>

    @Inject
    lateinit var profileVerifierLogger: ProfileVerifierLogger

    override fun onCreate() {
        super.onCreate()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
        Sync.initialize(context = this)
        // initializing the picker library
        UnsplashPhotoPicker.init(
            this,
            CustSecrets.UnsplashAccessKey,
            CustSecrets.UnsplashSecretKey
            /* optional page size (number of photos per page) */
        )
        /* .setLoggingEnabled(true) // if you want to see the http requests */
        profileVerifierLogger()
    }

    override fun newImageLoader(): ImageLoader = imageLoader.get()
}
