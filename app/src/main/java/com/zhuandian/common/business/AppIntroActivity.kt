package com.zhuandian.common.business

import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import kotlinx.android.synthetic.main.item_home_list.*

class AppIntroActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_app_intro

    override fun setUpView() {
        tv_title.text = "软件介绍"
    }


}
