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

public val UnsplashIcons.PhotoLibrary24px: ImageVector
    get() {
        if (_photoLibrary24px != null) {
            return _photoLibrary24px!!
        }
        _photoLibrary24px = Builder(name = "PhotoLibrary24px", defaultWidth = 24.0.dp, defaultHeight
                = 24.0.dp, viewportWidth = 960.0f, viewportHeight = 960.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(360.0f, 560.0f)
                lineTo(760.0f, 560.0f)
                lineTo(622.0f, 380.0f)
                lineTo(530.0f, 500.0f)
                lineTo(468.0f, 420.0f)
                lineTo(360.0f, 560.0f)
                close()
                moveTo(320.0f, 720.0f)
                quadTo(287.0f, 720.0f, 263.5f, 696.5f)
                quadTo(240.0f, 673.0f, 240.0f, 640.0f)
                lineTo(240.0f, 160.0f)
                quadTo(240.0f, 127.0f, 263.5f, 103.5f)
                quadTo(287.0f, 80.0f, 320.0f, 80.0f)
                lineTo(800.0f, 80.0f)
                quadTo(833.0f, 80.0f, 856.5f, 103.5f)
                quadTo(880.0f, 127.0f, 880.0f, 160.0f)
                lineTo(880.0f, 640.0f)
                quadTo(880.0f, 673.0f, 856.5f, 696.5f)
                quadTo(833.0f, 720.0f, 800.0f, 720.0f)
                lineTo(320.0f, 720.0f)
                close()
                moveTo(160.0f, 880.0f)
                quadTo(127.0f, 880.0f, 103.5f, 856.5f)
                quadTo(80.0f, 833.0f, 80.0f, 800.0f)
                lineTo(80.0f, 240.0f)
                lineTo(160.0f, 240.0f)
                lineTo(160.0f, 800.0f)
                quadTo(160.0f, 800.0f, 160.0f, 800.0f)
                quadTo(160.0f, 800.0f, 160.0f, 800.0f)
                lineTo(720.0f, 800.0f)
                lineTo(720.0f, 880.0f)
                lineTo(160.0f, 880.0f)
                close()
            }
        }
        .build()
        return _photoLibrary24px!!
    }

private var _photoLibrary24px: ImageVector? = null
