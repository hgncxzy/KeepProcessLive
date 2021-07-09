package com.xzy.study.keepprocesslive.onepixel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.xzy.study.keepprocesslive.R

/**
 *
 * @author ：created by xzy.
 * @date ：2021/7/6
 */
class LiveActivity : AppCompatActivity() {

    private val tag: String? = LiveActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        setContentView(R.layout.activity_one_pixel)

        window.setGravity(Gravity.START and Gravity.TOP)
        val attributes = window.attributes
        //宽高设计为一个像素
        attributes.width = 1
        attributes.height = 1
        //起始坐标
        attributes.x = 0
        attributes.y = 0
        window.attributes = attributes

        ScreenManager.getInstance(this).setActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    companion object {
        fun actionToLiveActivity(context: Context) {
            val intent = Intent(context, LiveActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }
}