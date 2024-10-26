/*
 * Copyright 2022 The Android Open Source Project
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

plugins {
    alias(libs.plugins.nowinandroid.android.feature)
    alias(libs.plugins.nowinandroid.android.library.compose)
    alias(libs.plugins.nowinandroid.android.library.jacoco)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.devwindsw.lifeonearth.feature.unsplash"
}

dependencies {
    implementation(libs.accompanist.permissions)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(project(":core:notifications"))
    implementation(libs.androidx.constraintlayout)

    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // For unsplash photos
    // Refer to https://github.com/unsplash/unsplash-photopicker-android/blob/master/README.md
    implementation("com.github.unsplash:unsplash-photopicker-android:1.0.1")

    // https://github.com/coil-kt/coil
    implementation("io.coil-kt.coil3:coil-compose:3.0.0-rc01")
    // Issue: Unable to create a fetcher that supports: (XXX)
    // Why: By default, Coil 3.x does not include support for network URLs.
    // Solution: You must add a dependency on one of Coil's network artifacts.
    // https://coil-kt.github.io/coil/network/
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.0-rc01") // Only available on Android/JVM.

    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.robolectric)
    testImplementation(projects.core.testing)
    testDemoImplementation(projects.core.screenshotTesting)

    androidTestImplementation(libs.bundles.androidx.compose.ui.test)
    androidTestImplementation(projects.core.testing)
}
