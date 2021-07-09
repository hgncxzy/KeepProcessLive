package com.xzy.study.keepprocesslive.onepixel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

/**
 *
 * @author ：created by xzy.
 * @date ：2021/7/6
 */
class ScreenBroadcastListener constructor(context: Context) {
    private val tag: String = ScreenBroadcastListener::class.java.simpleName
    var mContext: Context = context.applicationContext
    var screenBroadcastReceiver: ScreenBroadcastReceiver
    lateinit var screenStateListener: ScreenStateListener

    init {
        screenBroadcastReceiver = ScreenBroadcastReceiver()
    }


    fun registerListener(pScreenStateListener: ScreenStateListener) {
        screenStateListener = pScreenStateListener
        registerListener()
    }

    private fun registerListener() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        mContext.registerReceiver(screenBroadcastReceiver, intentFilter)
    }

    interface ScreenStateListener {
        fun onScreenOn()
        fun onScreenOff()
    }

    /**
     * Screen 状态广播接收者
     * */
    inner class ScreenBroadcastReceiver : BroadcastReceiver() {
        var action: String? = null
        override fun onReceive(context: Context?, intent: Intent?) {
            action = intent?.action
            Log.d(tag, "$action")
            when (action) {
                Intent.ACTION_SCREEN_ON -> {
                    screenStateListener.onScreenOn()
                }
                Intent.ACTION_SCREEN_OFF -> {
                    screenStateListener.onScreenOff()
                }
            }
        }
    }
}