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

package com.devwindsw.lifeonearth.feature.unsplash.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.devwindsw.lifeonearth.feature.unsplash.icons.UnsplashIcons

// Source from https://fonts.google.com/icons
// Generated by "Svg to Compose" of Pavel Shurmilov
public val UnsplashIcons.ZoomOut48px: ImageVector
    get() {
        if (_zoomOut48px != null) {
            return _zoomOut48px!!
        }
        _zoomOut48px = Builder(name = "ZoomOut48px", defaultWidth = 48.0.dp, defaultHeight =
                48.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(796.0f, 839.0f)
                lineTo(533.0f, 576.0f)
                quadTo(503.0f, 602.0f, 463.04f, 616.5f)
                quadTo(423.08f, 631.0f, 378.0f, 631.0f)
                quadTo(269.84f, 631.0f, 194.92f, 556.0f)
                quadTo(120.0f, 481.0f, 120.0f, 375.0f)
                quadTo(120.0f, 269.0f, 195.0f, 194.0f)
                quadTo(270.0f, 119.0f, 376.5f, 119.0f)
                quadTo(483.0f, 119.0f, 557.5f, 194.0f)
                quadTo(632.0f, 269.0f, 632.0f, 375.15f)
                quadTo(632.0f, 418.0f, 618.0f, 458.0f)
                quadTo(604.0f, 498.0f, 576.0f, 533.0f)
                lineTo(840.0f, 795.0f)
                lineTo(796.0f, 839.0f)
                close()
                moveTo(377.0f, 571.0f)
                quadTo(458.25f, 571.0f, 515.13f, 513.5f)
                quadTo(572.0f, 456.0f, 572.0f, 375.0f)
                quadTo(572.0f, 294.0f, 515.13f, 236.5f)
                quadTo(458.25f, 179.0f, 377.0f, 179.0f)
                quadTo(294.92f, 179.0f, 237.46f, 236.5f)
                quadTo(180.0f, 294.0f, 180.0f, 375.0f)
                quadTo(180.0f, 456.0f, 237.46f, 513.5f)
                quadTo(294.92f, 571.0f, 377.0f, 571.0f)
                close()
                moveTo(275.0f, 404.0f)
                lineTo(275.0f, 344.0f)
                lineTo(476.0f, 344.0f)
                lineTo(476.0f, 404.0f)
                lineTo(275.0f, 404.0f)
                close()
            }
        }
        .build()
        return _zoomOut48px!!
    }

private var _zoomOut48px: ImageVector? = null
