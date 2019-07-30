package com.zhuandian.common.business

import com.bumptech.glide.Glide
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.entity.HomeList
import kotlinx.android.synthetic.main.activity_home_list_item_detail.*
import kotlinx.android.synthetic.main.item_home_list.*
import kotlinx.android.synthetic.main.item_home_list.iv_img
import kotlinx.android.synthetic.main.item_home_list.tv_content
import kotlinx.android.synthetic.main.item_home_list.tv_time

class HomeListItemDetailActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_home_list_item_detail

    override fun setUpView() {
        tv_title.text = "咨询详情"
        var data: HomeList = intent.getSerializableExtra("data") as HomeList
        Glide.with(this).load(data.imgUrl).into(iv_img)
        tv_time.text = data.time
        tv_item_title.text = data.title
        tv_content.text = data.content

    }


}
