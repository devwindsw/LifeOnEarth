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

package com.devwindsw.lifeonearth.feature.shareddata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devwindsw.lifeonearth.feature.shareddata.SharedDataUiState.Loading
import com.google.samples.apps.nowinandroid.core.data.repository.SharedDataRepository
import com.google.samples.apps.nowinandroid.core.model.data.SharedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SharedDataViewModel @Inject constructor(
    private val sharedDataRepository: SharedDataRepository,
) : ViewModel() {
    val uiState: StateFlow<SharedDataUiState> =
        sharedDataRepository.getSharedData()
            .map<List<SharedData>, SharedDataUiState>(SharedDataUiState::Success)
            .onStart { emit(Loading) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5.seconds.inWholeMilliseconds),
                initialValue = Loading,
            )

    fun addSharedData(sharedData: List<SharedData>) {
        viewModelScope.launch {
            sharedDataRepository.addSharedData(sharedData)
        }
    }
}

sealed interface SharedDataUiState {
    data object Loading : SharedDataUiState
    data class Success(val sharedData: List<SharedData>) : SharedDataUiState
}