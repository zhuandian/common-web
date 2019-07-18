package com.zhuandian.common.business

import com.bumptech.glide.Glide
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.entity.HomeList
import kotlinx.android.synthetic.main.item_home_list.*

class ItemDetailActivity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_item_detail

    override fun setUpView() {
        var data: HomeList = intent.getSerializableExtra("data") as HomeList
        Glide.with(this).load(data.imgUrl).into(iv_img)
        tv_time.text = data.time
        tv_title.text = data.title
        tv_content.text = data.content

    }


}
