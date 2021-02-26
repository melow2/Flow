package com.jeit.flower

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * [Test01]
 *
 * - Flow가 CoroutineScope 아래에서 비동기적으로 수행되는 것을 확인.
 * - Flow 아래에서 방출할때마다 값을 가져오는 것.
 * - cold stream이고 collet 전까지 수행되지 않는다.
 * - collect를 호출 할 때 마다 실행된다.
 *
 * @author 권혁신
 * @version 1.0.0
 * @since 2021-02-26 오전 9:29
 **/
fun Test01(): Flow<Int> = flow{
    for(i in 1..3){
        delay(100)
        emit(i)
    }
}


fun runOnTest01() = CoroutineScope(Dispatchers.Main).launch{
    Log.d(MainActivity.TEST01,"[START]")

    launch {
        for(k in 1..3){
            Log.d(MainActivity.TEST01,"[OUT #2] $k")
            delay(100)
        }
    }

    Test01().collect { value ->
        Log.d(MainActivity.TEST01,"[OUT #1] $value")
    }

    Log.d(MainActivity.TEST01,"[END]")
}