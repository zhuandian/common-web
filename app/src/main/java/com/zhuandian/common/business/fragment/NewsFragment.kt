package com.zhuandian.common.business.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.zhuandian.common.R
import com.zhuandian.common.adapter.NewsAdapter
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.entity.NewsEntity
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.utils.MyJsonArrayRequest
import com.zhuandian.common.utils.MyJsonRequest
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * desc :
 * author：xiedong
 * date：2019/7/19
 */
class NewsFragment : BaseFragment() {
    lateinit var entityarr: Array<NewsEntity>
    override fun getLayoutId(): Int = R.layout.fragment_news

    override fun setUpView() {
        initTab()
        initData()
    }

    private fun initTab() {
        tab_layout.addTab(tab_layout.newTab().setText("全部"))
        tab_layout.addTab(tab_layout.newTab().setText("热搜"))
        tab_layout.addTab(tab_layout.newTab().setText("促销"))
        tab_layout.addTab(tab_layout.newTab().setText("优惠购"))
    }

    private fun initData() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        var jsonObjectRequest: MyJsonArrayRequest = MyJsonArrayRequest(Constant.NEWS_PAGE_URL,
                Response.Listener {
                    entityarr = Gson().fromJson(it.toString(), Array<NewsEntity>::class.java)
                    initList(entityarr)
                },
                Response.ErrorListener {
                })
        requestQueue.add(jsonObjectRequest)
    }

    private fun initList(entityarr: Array<NewsEntity>?) {
        rv_list.adapter = NewsAdapter(entityarr!!.toList(), activity)
        rv_list.layoutManager = LinearLayoutManager(activity)
    }
}