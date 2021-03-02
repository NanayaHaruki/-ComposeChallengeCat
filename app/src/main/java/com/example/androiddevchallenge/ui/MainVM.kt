package com.example.androiddevchallenge.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.data.DataProvider


class MainVM:ViewModel() {
    val dataSource = DataProvider()
    fun fetchData() = dataSource.provideDatas()

    /** 列表页点击条目时赋值 */
     var cat:Cat? by mutableStateOf<Cat?>(null)
}