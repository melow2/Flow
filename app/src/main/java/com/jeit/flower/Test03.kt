package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * [Test03]
 *
 * - map
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test03(): Flow<String> = (1..3).asFlow().map { request ->
    "request: $request"
}

fun runOnTest03() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST03, "[START]")

    launch {
        for (k in 1..3) {
            Log.d(MainActivity.TEST03, "[OUT #1] $k")
            delay(100)
        }
    }

    Test03().collect { value ->
        Log.d(MainActivity.TEST03, "[OUT #2] $value")
    }

    Log.d(MainActivity.TEST03, "[END]")
}