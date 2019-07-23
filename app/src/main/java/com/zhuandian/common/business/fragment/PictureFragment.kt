package com.zhuandian.common.business.fragment

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.zhuandian.common.R
import com.zhuandian.common.adapter.PictureAdapter
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.entity.PictureEntity
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.utils.MyJsonArrayRequest
import kotlinx.android.synthetic.main.fragment_picture.*
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
        tv_title.text = "精品爆款"

        initData();
    }

    private fun initData() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        var jsonArray: MyJsonArrayRequest = MyJsonArrayRequest(Constant.PICTURE_PAGE_URL,
                Response.Listener {
                    var entity: Array<PictureEntity> = Gson().fromJson(it.toString(), Array<PictureEntity>::class.java)
                    initRecyclerView(entity)
                },
                Response.ErrorListener {
                }
        )

        requestQueue.add(jsonArray)
    }

    private fun initRecyclerView(entity: Array<PictureEntity>) {
        var pictureAdapter = PictureAdapter(entity.toList(), activity)
        rv_list.adapter = pictureAdapter
        rv_list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        pictureAdapter.setOnShareClickListener(object : PictureAdapter.OnShareClickListener {
            override fun shareClick(pictureEntity: PictureEntity) {
                var shareIntent: Intent = Intent(Intent.ACTION_SEND)
                shareIntent.setType("text/plain")
                shareIntent.putExtra(Intent.EXTRA_TEXT, pictureEntity.img)
                startActivity(Intent.createChooser(shareIntent, "分享"))
            }

        })
    }


}