package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [Test03]
 *
 * - Terminal flow operators
 *
 * - toList() or toSet(): flow를 MutableList나 MutableSet으로 변환.
 * - first: 첫번째 원소를 반환하고 나머지는 cancel.
 * - reduce: 첫번째 원소에 주어진 operation을 이용하여 누적시켜 최종값을 반환.
 * - fold: 초기값을 입력받아 주어진 operation을 이요하여 누적시켜 최종값을 반환.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test06(): Flow<Int> = (1..100).asFlow().map { it*it }

fun runOnTest06() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST06, "[START]")

    Test06().take(5).reduce { state,change ->
        Log.d(MainActivity.TEST06, "[OUT #2] ${state+change}")
    }

    Log.d(MainActivity.TEST06, "[END]")
}