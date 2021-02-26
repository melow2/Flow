package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [Test08]
 *
 * - Sequential #2
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test08(): Flow<String> = (1..100).asFlow().filter {
    it%2==0
}.map {
    Log.d(MainActivity.TEST08, "[SEQUENCE #1]:: Current Thread -> ${Thread.currentThread().name}")
    "Result:: $it"
}.flowOn(Dispatchers.Default)

fun runOnTest08() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST08, "[START]")

    Test08().take(5).collect{
        Log.d(MainActivity.TEST08, "[OUT]:: Current Thread -> ${Thread.currentThread().name}")
    }

    Log.d(MainActivity.TEST08, "[END]")
}