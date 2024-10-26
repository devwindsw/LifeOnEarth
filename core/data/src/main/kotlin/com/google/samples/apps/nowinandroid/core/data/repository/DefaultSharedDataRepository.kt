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

package com.google.samples.apps.nowinandroid.core.data.repository

import com.google.samples.apps.nowinandroid.core.data.model.asEntity
import com.google.samples.apps.nowinandroid.core.database.dao.SharedDataDao
import com.google.samples.apps.nowinandroid.core.database.model.SharedDataEntity
import com.google.samples.apps.nowinandroid.core.database.model.asExternalModel
import com.google.samples.apps.nowinandroid.core.model.data.SharedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Disk storage backed implementation of the [SharedDataRepository].
 * Reads are exclusively from local storage to support offline access.
 */
internal class DefaultSharedDataRepository @Inject constructor(
    private val sharedDataDao: SharedDataDao,
) : SharedDataRepository {

    override fun getSharedData(): Flow<List<SharedData>> =
        sharedDataDao.getSharedDataEntities()
            .map { it.map(SharedDataEntity::asExternalModel) }

    /**
     * Add list of shared data
     */
    override suspend fun addSharedData(sharedData: List<SharedData>) {
        sharedDataDao.insertOrIgnoreSharedData(
            sharedDataEntities = sharedData
                .map(SharedData::asEntity)
                .distinct()
        )
    }
}