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

package com.devwindsw.lifeonearth.core.unsplash.model

import com.google.samples.apps.nowinandroid.core.model.data.ImageResource
import kotlinx.serialization.Serializable

/**
 * Network representation of Unsplash photo
 */
@Serializable
data class UnsplashNetworkPhotoUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val small_s3: String
)

@Serializable
data class UnsplashNetworkPhoto(
    val id: String,
    val slug: String = "",
    val alt_description: String = "",
    val urls: UnsplashNetworkPhotoUrls,
    val imageUrl: String = "",
    val followed: Boolean = false,
)

fun UnsplashNetworkPhoto.asExternalModel() = ImageResource(
    title = alt_description,
    url = getUrl(urls),
    headerImageUrl = urls.thumb
)

private fun getUrl(urls: UnsplashNetworkPhotoUrls): String {
    if (urls.raw != null && urls.raw.length > 0) return urls.raw
    if (urls.full != null && urls.full.length > 0) return urls.full
    if (urls.regular != null && urls.regular.length > 0) return urls.regular
    if (urls.small != null && urls.small.length > 0) return urls.small
    if (urls.small_s3 != null && urls.small_s3.length > 0) return urls.small_s3
    if (urls.thumb != null && urls.thumb.length > 0) return urls.thumb
    return ""
}