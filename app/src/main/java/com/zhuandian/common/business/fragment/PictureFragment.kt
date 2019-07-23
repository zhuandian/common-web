package com.zhuandian.common.business.fragment

import android.view.View
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseFragment
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 * desc :
 * author：xiedong
 * date：2019/7/19
 */
class PictureFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.fragment_picture

    override fun setUpView() {
        iv_back.visibility = View.GONE
        iv_share.visibility = View.GONE
        tv_title.text = "照片"
    }
}