package com.zhuandian.common.business.fragment

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.zhuandian.common.R
import com.zhuandian.common.adapter.NewsAdapter
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.NewsListItemActivity
import com.zhuandian.common.entity.NewsEntity
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.utils.MyJsonArrayRequest
import kotlinx.android.synthetic.main.fragment_news.*
import org.jetbrains.anko.startActivity

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
        tab_layout.addTab(tab_layout.newTab().setText("便宜购物"))
        tab_layout.addTab(tab_layout.newTab().setText("促销"))
        tab_layout.addTab(tab_layout.newTab().setText("网红推荐"))
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                filterDataList(p0?.position)
            }

        })
    }

    private fun filterDataList(position: Int?) {
        if (position == 0)  //全部
            initList(entityarr)
        else
            initList(entityarr.filter { it.type.toInt() == position }.toTypedArray())
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
        var newsAdapter = NewsAdapter(entityarr!!.toList(), activity)
        rv_list.adapter = newsAdapter
        rv_list.layoutManager = LinearLayoutManager(activity)
        newsAdapter.setOnClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(newsEntity: NewsEntity) {
                (activity as Activity).startActivity<NewsListItemActivity>(
//                        "data" to newsEntity
                        Pair("data", newsEntity)
                )
            }

        })
    }
}

