package com.example.androiddevchallenge.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ScrollableTabRow
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
                        error.throwable.localizedMessage
                    )
                }
            )
        }

    }
}