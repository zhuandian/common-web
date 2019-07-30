package com.zhuandian.common.business.fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.AppIntroActivity
import com.zhuandian.common.business.CommonListActivity
import com.zhuandian.common.business.FeedBackActivity
import com.zhuandian.common.business.login.LoginActivity
import com.zhuandian.common.utils.Constant
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
        ll_helper.setOnClickListener(this)
        ll_hot.setOnClickListener(this)
        ll_hotel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_feed_back ->
                (activity as Activity).startActivity<FeedBackActivity>()
            R.id.rl_new_version ->
                (activity as Activity).alert("当前已经最新版本", "升级提醒") { yesButton { it.cancel() } }.show()
            R.id.btn_logout -> {
                var sharedPreferences = activity?.getSharedPreferences("config", Context.MODE_PRIVATE)
                sharedPreferences!!.edit()
                        .putBoolean("isLogin", false)
                        .commit()
                (activity as Activity).startActivity<LoginActivity>()
                activity!!.finish()
            }
            R.id.rl_help ->
                (activity as Activity).startActivity<AppIntroActivity>()
            R.id.ll_helper -> {
                var intent = Intent(activity, CommonListActivity::class.java)
                intent.putExtra("type", 3)
                intent.putExtra("title", "行程助手")
                startActivity(intent)
            }
            R.id.ll_hotel -> {
                var intent = Intent(activity, CommonListActivity::class.java)
                intent.putExtra("type", 4)
                intent.putExtra("title", "酒店住宿")
                startActivity(intent)
            }

            R.id.ll_hot -> {
                var intent = Intent(activity, CommonListActivity::class.java)
                intent.putExtra("type", 2)
                intent.putExtra("title", "热门推荐")
                startActivity(intent)
            }

        }
    }

    private fun getAppVersion(): CharSequence? {
        var versionName = activity!!.packageManager.getPackageInfo(activity?.packageName, 0).versionName
        return versionName
    }
}