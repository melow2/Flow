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
 * - take
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test05(): Flow<String> = (1..100).asFlow().transform { request->
    try {
        emit("Making request::$request")
        emit("************************")
    }finally {
        Log.d(MainActivity.TEST05, "[ERROR]")
    }
}

fun runOnTest05() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST05, "[START]")
    launch {
        for (k in 1..3) {
            Log.d(MainActivity.TEST05, "[OUT #1] $k")
            delay(100)
        }
    }

    Test05().take(2).collect { value ->
        Log.d(MainActivity.TEST05, "[OUT #2] $value")
    }

    Log.d(MainActivity.TEST04, "[END]")
}