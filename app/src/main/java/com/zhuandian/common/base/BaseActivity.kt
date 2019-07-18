package com.zhuandian.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        setUpView()
    }

    abstract fun setUpView()

    abstract fun getLayoutId(): Int
}