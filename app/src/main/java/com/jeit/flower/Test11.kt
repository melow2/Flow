package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

/**
 * [Test09]
 *
 * - Processing the latest value
 * - 느린 collector가 이를 처리하기 전에 다른 값을 전달 받으면, 이전 collector를 취소하고 새로 전달받은 값을 처리하도록 재시작하도록 할 수 있다.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun TEST11(): Flow<Int> = (1..100).asFlow().map {
    delay(100)
    Log.d(MainActivity.TEST11, "[EMIT] -> $it")
    it
}

fun runOnTest11() = CoroutineScope(Dispatchers.Main).launch {
    Log.d(MainActivity.TEST11, "[START]")

    val time = measureTimeMillis {
        TEST10()
            .take(10)
            .collectLatest{
                try{
                    Log.d(MainActivity.TEST11, "[COLLECT] -> $it")
                    delay(300)
                    Log.d(MainActivity.TEST11, "[DONE] -> $it")
                }catch (ce:CancellationException){
                    Log.d(MainActivity.TEST11, "[CANCEL] -> $it")
                }

            }
    }
    Log.d(MainActivity.TEST11, "[END] - Collected in $time ms ")
}

