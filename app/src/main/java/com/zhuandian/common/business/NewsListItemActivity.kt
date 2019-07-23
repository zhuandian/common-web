package com.zhuandian.common.business

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.entity.NewsEntity
import kotlinx.android.synthetic.main.activity_news_list_item.*
import kotlinx.android.synthetic.main.item_home_list.iv_img
import kotlinx.android.synthetic.main.item_home_list.tv_content
import kotlinx.android.synthetic.main.item_home_list.tv_time
import kotlinx.android.synthetic.main.item_home_list.tv_title
import kotlinx.android.synthetic.main.layout_common_title.*

/**
 * desc :
 * author：xiedong
 * date：2019/7/23
 */
class NewsListItemActivity : BaseActivity() {


    private lateinit var data: NewsEntity


    override fun getLayoutId(): Int = R.layout.activity_news_list_item

    override fun setUpView() {
        tv_title.text = "咨询详情"
        data = intent.getSerializableExtra("data") as NewsEntity
        Glide.with(this).load(data.imgUrl).into(iv_img)
        tv_time.text = data.time
        tv_title.text = data.title
        tv_content.text = data.content
        tv_type.text = when (data.type.toInt()) {
            1 -> "#热搜#"
            2 -> "#便宜购物#"
            3 -> "#促销#"
            4 -> "#网红推荐#"
            else
            -> "#未知#"
        }
        iv_share.visibility = View.VISIBLE
        iv_share.setOnClickListener {
            var shareIntent: Intent = Intent(Intent.ACTION_SEND)
            shareIntent.setType("text/plain")
            shareIntent.putExtra(Intent.EXTRA_TEXT, data.title + data.content)
            startActivity(Intent.createChooser(shareIntent, "分享"))
//            startActivity(shareIntent)
        }
    }

}