package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

/**
 * [Test12]
 *
 * - Processing the latest value
 * - 느린 collector가 이를 처리하기 전에 다른 값을 전달 받으면, 이전 collector를 취소하고 새로 전달받은 값을 처리하도록 재시작하도록 할 수 있다.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun integerEmit() = (1..3).asFlow().onEach { delay(300) }
fun stringEmit() = flowOf("one","two","three").onEach { delay(400) }

fun runOnTest13() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST13, "[START]")
    val time = measureTimeMillis {
        integerEmit().combine(stringEmit()){ a,b -> "$a -> $b"}
            .collect { value ->
                Log.d(MainActivity.TEST13, "[COLLECT] Collect::$value")
            }

    }
    Log.d(MainActivity.TEST13, "[END] ** Collected in $time ms ** ")
}


