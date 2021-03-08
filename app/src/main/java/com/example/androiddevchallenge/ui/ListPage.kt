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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Cat
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState
import dev.chrisbanes.accompanist.imageloading.MaterialLoadingImage

@Composable
fun CatList(vm: MainVM) {
    val datas = vm.fetchData()
    LazyColumn(
        content = {
            items(datas) { cat ->
                CatItem(vm, cat)
            }
        }
    )
}

@Composable
fun CatItem(vm: MainVM, cat: Cat) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { vm.cat = cat }
            .fillMaxWidth()
    ) {
        Row() {
            CoilImage(
                data = cat.picList.first(),
                modifier = Modifier.size(120.dp),
            ) { imageLoadState: ImageLoadState ->
                when (imageLoadState) {
                    is ImageLoadState.Success -> {
                        MaterialLoadingImage(imageLoadState, null)
                    }
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = cat.name)
                Text(text = "性别:${cat.gender}")
                Text(text = "年龄:${cat.age}")
            }
        }
    }
}
