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
 * - Buffering
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun TEST09(): Flow<Int> = (1..100).asFlow().map {
    delay(100)
    it * it
}

fun runOnTest09() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST09, "[START]")

    val time = measureTimeMillis {
        TEST09()
            .take(3)
            .buffer() // emit()과 collect()을 순차적인 처리가 아닌 파이프라이닝을 통해 동시에 동작하도록 하여 시간을 감소 시킴.
            .collect {
            delay(300)
            Log.d(MainActivity.TEST09, "[OUT] -> ${it}")
        }
    }

    Log.d(MainActivity.TEST09, "[END] - Collected in $time ms ")
}

