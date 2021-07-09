package com.xzy.study.keepprocesslive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xzy.study.keepprocesslive.onepixel.ScreenBroadcastListener
import com.xzy.study.keepprocesslive.onepixel.ScreenManager

class MainActivity : AppCompatActivity(), ScreenBroadcastListener.ScreenStateListener {
    private val tag: String = MainActivity::class.java.simpleName

     lateinit var screenManager: ScreenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenManager = ScreenManager.getInstance(this)
        val listener = ScreenBroadcastListener(this)
        listener.registerListener(this)
        //todo 优化为服务实现，占用更小的内存
       // LiveService.toLiveService(this)
    }

    override fun onScreenOn() {
        Log.d(tag, "onScreenOn")
        screenManager.finishActivity()
    }

    override fun onScreenOff() {
        Log.d(tag, "onScreenOff")
        screenManager.startActivity()
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }
}