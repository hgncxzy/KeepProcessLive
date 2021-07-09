package com.xzy.study.keepprocesslive.onepixel

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 使用服务这种方式有缺陷，锁屏后，服务进程会被干掉。这个应该是版本兼容问题
 * @author ：created by xzy.
 * @date ：2021/7/6
 */
class LiveService : Service(), ScreenBroadcastListener.ScreenStateListener {
    private val tag: String = LiveService::class.java.simpleName
    private lateinit var screenManager: ScreenManager

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //屏幕锁屏时启动一个一像素的Activity，屏幕点亮时关闭这个Activity
        screenManager = ScreenManager.getInstance(this)
        val listener = ScreenBroadcastListener(this)
        listener.registerListener(this)

        return START_REDELIVER_INTENT
    }

    companion object {
        fun toLiveService(context: Context) {
            val intent = Intent(context, LiveService::class.java)
            context.startService(intent)
        }
    }

    override fun onScreenOn() {
        Log.d(tag, "onScreenOn:$screenManager")
        screenManager.finishActivity()
    }

    override fun onScreenOff() {
        Log.d(tag, "onScreenOff:$screenManager")
        screenManager.startActivity()
    }
}