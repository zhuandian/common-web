package com.zhuandian.common

import android.app.Application

import cn.jpush.android.api.JPushInterface

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}