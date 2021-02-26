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
 * - transform
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test04(): Flow<String> = (1..3).asFlow().transform { request->
    emit("Making request::$request")
    emit("************************")
}

fun runOnTest04() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST04, "[START]")
    launch {
        for (k in 1..3) {
            Log.d(MainActivity.TEST04, "[OUT #1] $k")
            delay(100)
        }
    }

    Test04().collect { value ->
        Log.d(MainActivity.TEST04, "[OUT #2] $value")
    }

    Log.d(MainActivity.TEST04, "[END]")
}