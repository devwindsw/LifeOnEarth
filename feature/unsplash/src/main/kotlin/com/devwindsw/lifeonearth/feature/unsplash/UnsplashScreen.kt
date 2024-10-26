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

package com.devwindsw.lifeonearth.feature.unsplash

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.devwindsw.lifeonearth.feature.unsplash.ImageResourceUiState.Loading
import com.devwindsw.lifeonearth.feature.unsplash.ImageResourceUiState.Success
import com.devwindsw.lifeonearth.feature.unsplash.icons.Download48px
import com.devwindsw.lifeonearth.feature.unsplash.icons.UnsplashIcons
import com.devwindsw.lifeonearth.feature.unsplash.icons.ZoomIn48px
import com.google.samples.apps.nowinandroid.core.designsystem.component.NiaOverlayLoadingWheel
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.data.UnsplashUrls
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity

private const val useUnsplashPhotoPicker = false

@Composable
internal fun UnsplashScreen(
    modifier: Modifier = Modifier,
    viewModel: UnsplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val picked = remember { mutableStateListOf<UnsplashPhoto>() }
    if (useUnsplashPhotoPicker) {
        var requested by remember { mutableStateOf(false) }
        val context = LocalContext.current
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    val data = result.data
                    val photos: ArrayList<UnsplashPhoto>? =
                        data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
                    if (photos == null || photos.isEmpty()) picked.clear() else picked.addAll(photos)
                }
            }
        }
        SideEffect {
            if (!requested) {
                requested = true
                launcher.launch(
                    UnsplashPickerActivity.getStartingIntent(
                        callingContext = context,
                        isMultipleSelection = false
                    )
                )
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val loadingContentDescription = stringResource(id = R.string.feature_unsplash_loading)

        when (uiState) {
            Loading -> {
                NiaOverlayLoadingWheel(
                    modifier = Modifier
                        .align(Alignment.Center),
                    contentDesc = loadingContentDescription,
                )
            }

            is Success -> {
                Column {
                    if (useUnsplashPhotoPicker) {
                        if (picked.isNotEmpty()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context = LocalContext.current)
                                    .data(getUrl(picked.get(0).urls))
                                    .crossfade(true).build(),
                                error = painterResource(R.drawable.ic_broken_image),
                                placeholder = painterResource(R.drawable.loading_img),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    } else {
                        val imageResource = (uiState as Success).imageResource
                        if (imageResource.isNotEmpty()) {
                            AsyncImage(
                                model = ImageRequest.Builder(context = LocalContext.current)
                                    .data(imageResource.get(0).url)
                                    .crossfade(true).build(),
                                error = painterResource(R.drawable.ic_broken_image),
                                placeholder = painterResource(R.drawable.loading_img),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth(),
                                /* For debugging
                                onState = { state ->
                                    when (state) {
                                        is AsyncImagePainter.State.Success -> {
                                            Log.i("UnsplashScreen", "image load success")
                                        }

                                        is AsyncImagePainter.State.Error -> {
                                            Log.i("UnsplashScreen", "error: ${state.result.throwable.message}")
                                        }

                                        else -> {}
                                    }
                                },*/
                            )
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        ButtonsGroup()
    }
}

@Composable
fun ButtonsGroup() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(vMargin = 8.dp, hMargin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(vMargin = 4.dp, hMargin = 16.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            IconButton(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button1")
            ) {
                Icon(
                    imageVector = UnsplashIcons.Download48px,
                    contentDescription = null,
                )
            }
            IconButton(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button2")
            ) {
                Icon(
                    imageVector = UnsplashIcons.ZoomIn48px,
                    contentDescription = null,
                )
            }
        }
    }
}

private fun getUrl(urls: UnsplashUrls): String {
    if (urls.raw != null) return urls.raw!!
    if (urls.full != null) return urls.full!!
    if (urls.large != null) return urls.large!!
    if (urls.regular != null) return urls.regular!!
    if (urls.medium != null) return urls.medium!!
    return urls.small
}

private fun decoupledConstraints(vMargin: Dp, hMargin: Dp): ConstraintSet {
    return ConstraintSet {
        val button1 = createRefFor("button1")
        val button2 = createRefFor("button2")

        constrain(button1) {
            bottom.linkTo(button2.top, margin = vMargin)
            end.linkTo(parent.end,hMargin)
        }
        constrain(button2) {
            bottom.linkTo(parent.bottom, vMargin)
            end.linkTo(parent.end, hMargin)
        }
    }
}
