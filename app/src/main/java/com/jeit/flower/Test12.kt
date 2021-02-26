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
fun TEST12(): Flow<String> = flow{
    for(i in 1..3){
        Log.d(MainActivity.TEST12, "[EMIT] $i")
        emit(i)
    }
}.map { value ->
    check(value <=1) { Log.d(MainActivity.TEST12, "[EXCEPTION] Crash on ::$value")}
    "Result $value"
}

fun runOnTest12() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST12, "[START]")
    val time = measureTimeMillis {
        TEST12()
            .catch { e -> emit("[EMIT_CRASH] ::$e") }
            .collect{
                Log.d(MainActivity.TEST12, "[COLLECT] Collect:: $it")
            }

    }
    Log.d(MainActivity.TEST11, "[END] ** Collected in $time ms ** ")
}

