package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

/**
 * [Test09]
 *
 * - Conflation
 * - flow는 연속적으로 값을 처리하여 방출한다.
 * - 마지막 최신값만 의미있는 값이라고 할 수 있음.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun TEST10(): Flow<Int> = (1..100).asFlow().map {
    delay(100)
    Log.d(MainActivity.TEST10, "[EMIT] -> $it")
    it
}

fun runOnTest10() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST10, "[START]")

    val time = measureTimeMillis {
        TEST10()
            .take(50)
            .conflate() // 중간 값을 버림..
            .collect {
            delay(300)
            Log.d(MainActivity.TEST10, "[COLLECT] -> $it")
        }
    }

    Log.d(MainActivity.TEST10, "[END] - Collected in $time ms ")
}

