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
package com.example.androiddevchallenge

import org.junit.Test
import java.util.Stack

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    // Add unit tests here
    @Test
    fun test() {
    }

    class MyQueue() {
        val s1 = Stack<Int>()
        val s2 = Stack<Int>()

        /** Initialize your data structure here. */

        var first: Int? = null

        /** Push element x to the back of queue. */
        fun push(x: Int) {
            // 添加新元素，现将s1转到s2
            // [2 1] [] ->[] [ 1 2 ]
            // 将新元素加入s1，再将s2挪回来
            // [3 2 1] []
            while (s1.isNotEmpty()) {
                s2.push(s1.pop())
            }
            s1.push(x)
            while (s2.isNotEmpty()) {
                s1.push(s2.pop())
            }
        }

        /** Removes the element from in front of queue and returns that element. */
        fun pop(): Int {
            return s1.pop()
        }

        /** Get the front element. */
        fun peek(): Int {
            return s1.peek()
        }

        /** Returns whether the queue is empty. */
        fun empty(): Boolean {
            return s1.empty()
        }
    }

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
}
