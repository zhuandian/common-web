package com.zhuandian.common.business.fragment

import android.app.Activity
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.FeedBackActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_common_title.*
import org.jetbrains.anko.startActivity

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */
class SettingFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun setUpView() {
        tv_title.text = "设置"
        btn_feed_back.setOnClickListener { (activity as Activity).startActivity<FeedBackActivity>() }
        tv_version.text = getAppVersion()
    }

    private fun getAppVersion(): CharSequence? {
        var versionName = activity!!.packageManager.getPackageInfo(activity?.packageName, 0).versionName
        return versionName
    }
}