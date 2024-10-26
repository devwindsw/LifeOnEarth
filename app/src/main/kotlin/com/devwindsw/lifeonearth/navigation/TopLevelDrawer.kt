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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.devwindsw.lifeonearth.R
import com.devwindsw.lifeonearth.navigation.TopLevelDestination.BOOKMARKS
import com.devwindsw.lifeonearth.navigation.TopLevelDestination.FOR_YOU
import com.devwindsw.lifeonearth.navigation.TopLevelDestination.INTERESTS
import com.devwindsw.lifeonearth.navigation.TopLevelDestination.UNSPLASH
import com.devwindsw.lifeonearth.ui.NiaAppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private data class CustDrawerItem(
    val titleId: Int,
    val onClick: () -> Unit = {},
)

@Composable
fun CustDrawer(
    appState: NiaAppState,
    drawerState: DrawerState,
    scope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        CustDrawerItem(
            titleId = FOR_YOU.iconTextId,
            onClick = {
                appState.navigateToTopLevelDestination(FOR_YOU)
                scope.launch {
                    drawerState.close()
                }
            },
        ),
        CustDrawerItem(
            titleId = BOOKMARKS.iconTextId,
            onClick = {
                appState.navigateToTopLevelDestination(BOOKMARKS)
                scope.launch {
                    drawerState.close()
                }
            },
        ),
        CustDrawerItem(
            titleId = INTERESTS.iconTextId,
            onClick = {
                appState.navigateToTopLevelDestination(INTERESTS)
                scope.launch {
                    drawerState.close()
                }
            }
        ),

        CustDrawerItem(
            titleId = UNSPLASH.iconTextId,
            onClick = {
                appState.navigateToTopLevelDestination(UNSPLASH)
                scope.launch {
                    drawerState.close()
                }
            },
        ),
    )
    ModalDrawerSheet {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_splash),
                contentDescription = stringResource(R.string.app_name)
            )
            Text(
                text = stringResource(R.string.app_name),
                modifier = Modifier.padding(16.dp)
            )
        }
        HorizontalDivider()
        items.forEach() {
            NavigationDrawerItem(
                label = { Text(text = stringResource(it.titleId)) },
                selected = false,
                onClick = it.onClick
            )
        }
    }
}