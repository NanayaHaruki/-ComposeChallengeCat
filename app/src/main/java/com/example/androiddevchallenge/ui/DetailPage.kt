/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Cat
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState

@Composable
fun DetailPage(cat: Cat) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(ScrollState(1))
    ) {
        Text(text = cat.name)
        Text(text = "送养人：${cat.preMaster}")
        Text(text = "年龄：${cat.age}")
        Text(text = "性别：${cat.gender}")
        Text(text = "微信：${cat.wechat}")
        Text(text = "城市：${cat.city}")
        Text(text = "描述：${cat.desc}")
        cat.picList.forEach {
            CoilImage(
                data = it,
                modifier = Modifier.fillMaxWidth(),
                contentDescription = cat.name,
                contentScale = ContentScale.Crop,
                fadeIn = true,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                onRequestCompleted = { imageLoadState ->
                    Log.d(
                        "loadImg",
                        imageLoadState.toString()
                    )
                },
                error = { error: ImageLoadState.Error ->
                    Log.d(
                        "loadImg",
                        error.throwable.localizedMessage ?: ""
                    )
                }
            )
        }
    }
}