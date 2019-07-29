package com.zhuandian.common.business.fragment

import android.app.Activity
import android.view.View
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.AppIntroActivity
import com.zhuandian.common.business.FeedBackActivity
import com.zhuandian.common.business.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_common_title.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */
class SettingFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutId(): Int = R.layout.fragment_setting

    override fun setUpView() {
        iv_back.visibility = View.GONE
        iv_share.visibility = View.GONE
        tv_title.text = "设置"
        rl_feed_back.setOnClickListener(this)
        tv_version.text = getAppVersion()
        rl_new_version.setOnClickListener(this)
        btn_logout.setOnClickListener(this)
        rl_help.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_feed_back ->
                (activity as Activity).startActivity<FeedBackActivity>()
            R.id.rl_new_version ->
                (activity as Activity).alert("当前已经最新版本", "升级提醒") { yesButton { it.cancel() } }.show()
            R.id.btn_logout -> {
                (activity as Activity).startActivity<LoginActivity>()
                activity!!.finish()
            }
            R.id.rl_help ->
                (activity as Activity).startActivity<AppIntroActivity>()

        }
    }

    private fun getAppVersion(): CharSequence? {
        var versionName = activity!!.packageManager.getPackageInfo(activity?.packageName, 0).versionName
        return versionName
    }
}