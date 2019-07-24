package com.zhuandian.common.business


import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_feed_back.*
import kotlinx.android.synthetic.main.item_home_list.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.yesButton

class FeedBackActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_feed_back
    override fun setUpView() {
        tv_title.text = "反馈建议"
        btn_submit.setOnClickListener {
            alert("我们已经收到您的反馈", "提交成功") { yesButton { it.cancel() } }.show()
        }
    }
}
