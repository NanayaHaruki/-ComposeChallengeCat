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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

enum class CountDownState { PAUSE, COUNTING, READY }

val clockNums = mutableListOf<String>().apply {
    repeat(60) {
        add(it.toTimeString())
    }
}

var hour by mutableStateOf(0)
var minute by mutableStateOf(0)
var second by mutableStateOf(0)
var state by mutableStateOf(CountDownState.READY)

fun Int.toTimeString() =
    if (this < 10) "0$this"
    else "$this"

@Composable
fun CountDown() {
    if (state == CountDownState.READY) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (numPickerRow, btnStart) = createRefs()
            Row(
                modifier = Modifier
                    .constrainAs(numPickerRow) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Numbers(1)
                Text(text = "时")
                Numbers(2)
                Text(text = "分")
                Numbers(3)
                Text(text = "秒")
            }
            Button(
                onClick = { startCountDown() },
                modifier = Modifier.constrainAs(btnStart) {
                    bottom.linkTo(parent.bottom, margin = 100.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                Text("开始", fontSize = 16.sp)
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${hour.toTimeString()}:${minute.toTimeString()}:${second.toTimeString()}",
                fontSize = 45.sp
            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = { state = CountDownState.READY }) {
                    Text("停止")
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(
                    onClick = {
                        if (state == CountDownState.PAUSE) {
                            state = CountDownState.COUNTING
                        } else if (state == CountDownState.COUNTING) {
                            state = CountDownState.PAUSE
                        }
                    }
                ) {
                    Text(
                        text = if (state == CountDownState.PAUSE) {
                            "继续"
                        } else if (state == CountDownState.COUNTING) {
                            "暂停"
                        } else ""
                    )
                }
            }
        }
    }
}

val timer: Timer = Timer(false)
var timerTask: TimerTask? = null
fun startCountDown() {
    Log.d("startCountDown", "click start $state")
    if (hour == 0 && minute == 0 && second == 0) {
        Log.d("startCountDown", "not set time")
        return
    }
    state = CountDownState.COUNTING
    timerTask = timerTask {
        if (state == CountDownState.PAUSE) return@timerTask
        if (state == CountDownState.READY) timerTask?.cancel()
        if (second > 0) {
            second -= 1
        } else if (second == 0) {
            if (minute > 0) {
                minute -= 1
                second = 59
            } else if (minute == 0) {
                if (hour > 0) {
                    hour -= 1
                    minute = 59
                    second = 59
                } else {
                    state = CountDownState.READY
                }
            }
        }
    }
    timer.schedule(timerTask, 0, 1000)
}

@Composable
fun Numbers(type: Int) {
    Column(
        modifier = Modifier
            .height(250.dp)
            .verticalScroll(ScrollState(1)),
        verticalArrangement = Arrangement.Center
    ) {
        clockNums.forEach {
            val timeNum = it.toInt()
            TextButton(
                onClick = {
                    when (type) {
                        1 -> hour = timeNum
                        2 -> minute = timeNum
                        3 -> second = timeNum
                    }
                },
            ) {
                Text(
                    it, fontSize = 20.sp,
                    color = if (type == 1 && hour == timeNum) {
                        Color.Red
                    } else if (type == 2 && minute == timeNum) {
                        Color.Red
                    } else if (type == 3 && second == timeNum) {
                        Color.Red
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun Preview() {
    CountDown()
}
