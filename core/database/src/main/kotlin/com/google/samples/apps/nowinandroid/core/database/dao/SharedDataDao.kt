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

package com.google.samples.apps.nowinandroid.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.samples.apps.nowinandroid.core.database.model.SharedDataEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [SharedDataEntity] access
 */
@Dao
interface SharedDataDao {
    @Query(value = "SELECT * FROM shared_data")
    fun getSharedDataEntities(): Flow<List<SharedDataEntity>>

    /**
     * Inserts [sharedDataEntities] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreSharedData(sharedDataEntities: List<SharedDataEntity>): List<Long>

    /**
     * Deletes rows in the db matching the specified [ids]
     */
    @Query(
        value = """
            DELETE FROM shared_data
            WHERE _id in (:ids)
        """,
    )
    suspend fun deleteSharedData(ids: List<String>)
}