package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * [Test02]
 *
 * - 값이 고정되어 있을 경우 flowOf를 사용.
 * - 다양한 Collection들을 asFlow로 flow로 변경가능.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test02(): Flow<Int> = flowOf(1,2,3)

fun runOnTest02() = CoroutineScope(Dispatchers.Main).launch{
    Log.d(MainActivity.TEST02,"[START]")

    launch {
        for(k in 1..3){
            Log.d(MainActivity.TEST02,"[OUT #1] $k")
            delay(100)
        }
    }

    Test02().collect { value ->
        Log.d(MainActivity.TEST02,"[OUT #2] $value")
    }

    (1..3).asFlow().collect { value ->   Log.d(MainActivity.TEST02,"[OUT #3] $value") }

    Log.d(MainActivity.TEST02,"[END]")
}