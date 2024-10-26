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

@file:Suppress("ktlint:standard:max-line-length")

package com.devwindsw.lifeonearth.feature.shareddata

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.devwindsw.lifeonearth.feature.shareddata.R.string
import com.devwindsw.lifeonearth.feature.shareddata.SharedDataUiState.Loading
import com.devwindsw.lifeonearth.feature.shareddata.SharedDataUiState.Success
import com.google.samples.apps.nowinandroid.core.model.data.SharedData

@Composable
fun SharedDataDialog(
    text: String,
    subject: String?,
    onDismiss: () -> Unit,
    viewModel: SharedDataViewModel = hiltViewModel(),
) {
    val configuration = LocalConfiguration.current
    val title = if (subject == null) text else subject
    val sharedData = listOf(SharedData(subject = subject, url = text))
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            HorizontalDivider()
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.titleSmall,
                )
                HorizontalDivider(Modifier.padding(top = 8.dp))
                when (uiState) {
                    Loading -> {
                    }

                    is Success -> {
                        val sharedData = (uiState as Success).sharedData
                        // TODO [LifeOnEarth] Define a string resource
                        Text(
                            text = "The number of items already saved: " + sharedData.size
                        )
                        sharedData.forEach() {
                            HorizontalDivider(Modifier.padding(top = 8.dp))
                            Text(
                                text = "#${it.id}: "
                                    + if (it.subject == null) it.url else it.subject!!,
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
                HorizontalDivider(Modifier.padding(top = 8.dp))
            }
        },
        confirmButton = {
            Text(
                text = stringResource(string.feature_shareddata_dismiss_dialog_button_text),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        saveAndDismiss(
                            sharedData = sharedData,
                            onDismiss = onDismiss,
                            viewModel = viewModel
                        )},
            )
        },
    )
}

private fun saveAndDismiss(
    sharedData: List<SharedData>,
    onDismiss: () -> Unit,
    viewModel: SharedDataViewModel
) {
    viewModel.addSharedData(sharedData)
    onDismiss()
}