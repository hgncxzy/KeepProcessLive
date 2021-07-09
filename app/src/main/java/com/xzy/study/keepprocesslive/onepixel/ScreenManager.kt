package com.xzy.study.keepprocesslive.onepixel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import java.lang.ref.WeakReference

/**
 *
 * @author ：created by xzy.
 * @date ：2021/7/6
 */
@SuppressLint("StaticFieldLeak")
class ScreenManager constructor(context: Context) {
    private val tag: String = ScreenManager::class.java.simpleName

    companion object {
        private lateinit var acWeakRef: WeakReference<LiveActivity>
        private lateinit var mContext: Context
        var screenManager: ScreenManager? = null

        fun getInstance(context: Context): ScreenManager {
            mContext = context
            if (screenManager == null) {
                screenManager = ScreenManager(context.applicationContext)
            }
            return screenManager!!
        }
    }

    fun setActivity(activity: LiveActivity) {
        acWeakRef = WeakReference(activity)
        Log.d(tag, "1----------acWeakRef:$acWeakRef")
    }

    fun startActivity() {
        LiveActivity.actionToLiveActivity(mContext)
    }

    fun finishActivity() {
        Log.d(tag, "2----------acWeakRef:$acWeakRef")
         acWeakRef.get()?.finish()
    }
}