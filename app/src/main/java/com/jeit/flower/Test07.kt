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
 * - Sequential
 *
 *
 *
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test07(): Flow<String> = (1..100).asFlow().filter {
    Log.d(MainActivity.TEST07, "[SEQUENCE #1]:: $it")
    it%2==0
}.map {
    Log.d(MainActivity.TEST07, "[SEQUENCE #2]:: $it")
    "Result:: $it"
}

fun runOnTest07() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST07, "[START]")

    Test07().take(5).collect{
        Log.d(MainActivity.TEST07, "[OUT #2] $it")
    }

    Log.d(MainActivity.TEST07, "[END]")
}