package com.jeit.flower

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runOnTest01()
        runOnTest02()
        runOnTest03()
        runOnTest04()
        runOnTest05()
        runOnTest06()
        runOnTest07()
        runOnTest08()
        runOnTest09()
        runOnTest10()
        runOnTest11()
        runOnTest12()
    }

    companion object{
        const val TEST01 = "TEST01"
        const val TEST02 = "TEST02"
        const val TEST03 = "TEST03"
        const val TEST04 = "TEST04"
        const val TEST05 = "TEST05"
        const val TEST06 = "TEST06"
        const val TEST07 = "TEST07"
        const val TEST08 = "TEST08"
        const val TEST09 = "TEST09"
        const val TEST10 = "TEST10"
        const val TEST11 = "TEST11"
        const val TEST12 = "TEST12"
        const val TEST13 = "TEST13"

    }
}