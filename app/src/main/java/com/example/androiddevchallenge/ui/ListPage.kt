package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.Cat
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState

@Composable
fun CatList(vm: MainVM) {
    val datas = vm.fetchData()
    LazyColumn(content = {
        items(datas) { cat ->
            CatItem(vm, cat)
        }
    })

}

@Composable
fun CatItem(vm: MainVM, cat: Cat) {
    Card(modifier = Modifier
        .padding(16.dp)
        .clickable { vm.cat = cat }
        .fillMaxWidth()
    ) {
        Row() {
            CoilImage(
                data = cat.picList.first(),
                modifier = Modifier.size(120.dp),
                contentDescription = cat.name,
                contentScale= ContentScale.Crop,
                fadeIn = true,
                loading = {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                onRequestCompleted = {imageLoadState -> Log.d("loadImg",imageLoadState.toString()) },
                error = {error: ImageLoadState.Error -> Log.d("loadImg",error.throwable.localizedMessage) }
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = cat.name)
                Text(text = "性别:${cat.gender}")
                Text(text = "年龄:${cat.age}")
            }
        }
    }

}