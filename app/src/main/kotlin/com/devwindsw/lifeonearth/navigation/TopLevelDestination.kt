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

package com.devwindsw.lifeonearth.navigation

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.devwindsw.lifeonearth.R
import com.devwindsw.lifeonearth.feature.unsplash.icons.PhotoLibrary24px
import com.devwindsw.lifeonearth.feature.unsplash.icons.PhotoLibraryBorder24px
import com.devwindsw.lifeonearth.feature.unsplash.icons.UnsplashIcons
import com.devwindsw.lifeonearth.feature.unsplash.navigation.UnsplashRoute
import com.google.samples.apps.nowinandroid.core.designsystem.icon.NiaIcons
import com.google.samples.apps.nowinandroid.feature.bookmarks.navigation.BookmarksRoute
import com.google.samples.apps.nowinandroid.feature.foryou.navigation.ForYouRoute
import com.google.samples.apps.nowinandroid.feature.interests.navigation.InterestsRoute
import kotlin.reflect.KClass
import com.devwindsw.lifeonearth.feature.unsplash.R as unsplashR
import com.google.samples.apps.nowinandroid.feature.bookmarks.R as bookmarksR
import com.google.samples.apps.nowinandroid.feature.foryou.R as forYouR
import com.google.samples.apps.nowinandroid.feature.search.R as searchR

/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int,
    val route: KClass<*>,
) {
    FOR_YOU(
        selectedIcon = NiaIcons.Upcoming,
        unselectedIcon = NiaIcons.UpcomingBorder,
        iconTextId = forYouR.string.feature_foryou_title,
        titleTextId = R.string.app_name,
        route = ForYouRoute::class,
    ),
    BOOKMARKS(
        selectedIcon = NiaIcons.Bookmarks,
        unselectedIcon = NiaIcons.BookmarksBorder,
        iconTextId = bookmarksR.string.feature_bookmarks_title,
        titleTextId = bookmarksR.string.feature_bookmarks_title,
        route = BookmarksRoute::class,
    ),
    INTERESTS(
        selectedIcon = NiaIcons.Grid3x3,
        unselectedIcon = NiaIcons.Grid3x3,
        iconTextId = searchR.string.feature_search_interests,
        titleTextId = searchR.string.feature_search_interests,
        route = InterestsRoute::class,
    ),
    UNSPLASH(
        selectedIcon = UnsplashIcons.PhotoLibrary24px,
        unselectedIcon = UnsplashIcons.PhotoLibraryBorder24px,
        iconTextId = unsplashR.string.feature_unsplash_title,
        titleTextId = unsplashR.string.feature_unsplash_title,
        route = UnsplashRoute::class,
    ),
}
