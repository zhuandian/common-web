package com.zhuandian.common.business

import android.content.Intent
import android.os.Handler
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.business.login.LoginActivity
import com.zhuandian.common.entity.ConfigEntity
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.utils.MyJsonRequest
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    private var type: Int = 0 // 0 显示原生 ，1 跳转h5

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun setUpView() {
        var requestQueue = Volley.newRequestQueue(this)
        var jsonObjectRequest = MyJsonRequest(Constant.APP_CONFIG_URL, null,
                Response.Listener {
                    var configEntity = Gson().fromJson(it.toString(), ConfigEntity::class.java)
                    Constant.H5_URL = configEntity.url
                    type = configEntity.type
                },
                Response.ErrorListener {
                }
        )
        requestQueue.add(jsonObjectRequest)

        Handler().postDelayed(object : Runnable {
            override fun run() {
                if (type == 0) {
                    startActivity<LoginActivity>()
                } else {
                    startActivity<MainWebActivity>()
                }
                finish()
            }
        }, 2000)
    }


}
