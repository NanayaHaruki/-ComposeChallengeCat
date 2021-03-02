package com.example.androiddevchallenge.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.Cat

@Composable
fun DetailPage(cat: Cat) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = cat.name)
        Text(text = "送养人：${cat.preMaster}")
        Text(text = "年龄：${cat.age}")
        Text(text = "性别：${cat.gender}")
        Text(text = "微信：${cat.wechat}")
        Text(text = "城市：${cat.city}")
        Text(text = "描述：${cat.desc}")
    }
}