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

package com.devwindsw.lifeonearth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.devwindsw.lifeonearth.feature.shareddata.SharedDataDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SharedDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            intent?.action == Intent.ACTION_SEND && intent.type == "text/plain" -> {
                handleSharedDataSingleText(intent) // Handle text being sent
            }
            else -> {
                // Unexpected data type
                handleNotSupportedType()
            }
        }
    }

    private fun handleSharedDataSingleText(intent: Intent) {
        val text = intent.getStringExtra(Intent.EXTRA_TEXT)
        // Handle only HTTP URLs
        if (text != null && text.startsWith("http")) {
            val subject = intent.getStringExtra(Intent.EXTRA_SUBJECT)
            enableEdgeToEdge()
            setContent {
                SharedDataDialog(
                    text = text,
                    subject = subject,
                    onDismiss = { finish() },
                )
            }
        } else {
            handleNotSupportedType()
        }
    }

    private fun handleNotSupportedType() {
        Toast.makeText(this, getString(R.string.not_supported_data_type), Toast.LENGTH_SHORT).show()
        finish()
    }
}