package com.zhuandian.common.business


import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import org.jetbrains.anko.alert

class FeedBackActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_feed_back
    override fun setUpView() {
        alert { "提交成功" }.show()
    }


}
