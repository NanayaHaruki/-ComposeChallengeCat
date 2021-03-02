package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.androiddevchallenge.data.Cat

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
        Row(modifier = Modifier
            .padding(16.dp)
            .clickable { vm.cat = cat }
        ) {
//        Image(painterResource(id = ),)
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = cat.name)
                Text(text = "性别:${cat.gender}")
                Text(text = "年龄:${cat.age}")
            }
        }
    }

}