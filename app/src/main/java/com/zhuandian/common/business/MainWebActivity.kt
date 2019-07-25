package com.zhuandian.common.business

import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.utils.Constant
import kotlinx.android.synthetic.main.activity_main_web.*
import org.jetbrains.anko.toast
import java.util.*

class MainWebActivity : BaseActivity() {


    override fun getLayoutId(): Int = R.layout.activity_main_web
    override fun setUpView() {
        wbPage.settings.javaScriptEnabled = true
        wbPage.settings.domStorageEnabled = true
        wbPage.webViewClient = WebViewClient()
        wbPage.webChromeClient = WebChromeClient()

        wbPage.loadUrl(Constant.H5_URL)
    }


    private var isCanBack: Boolean = false
    override fun onBackPressed() {
        if (wbPage.canGoBack())
            wbPage.goBack()
        else {
            if (isCanBack) {
                super.onBackPressed()
            } else {
                toast("再按一次退出应用")
                isCanBack = true
                var timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        isCanBack = false
                    }
                }, 3000)
            }
        }

    }

}
