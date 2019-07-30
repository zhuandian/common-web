package com.zhuandian.common.business

import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.zhuandian.common.R
import com.zhuandian.common.adapter.NewsAdapter
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.entity.NewsEntity
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.utils.MyJsonArrayRequest
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.item_home_list.*
import org.jetbrains.anko.startActivity

class CommonListActivity : BaseActivity() {

    private var type: Int = 1
    lateinit var entityarr: Array<NewsEntity>

    override fun getLayoutId(): Int = R.layout.activity_common_list

    override fun setUpView() {
        var title = intent.getStringExtra("title")
        type = intent.getIntExtra("type", 1)
        initData()
        tv_title.text = title
        refresh_layout.setOnRefreshListener {
            if (refresh_layout.isRefreshing && entityarr.size > 0) {
                refresh_layout.isRefreshing = false
                filterDataList(type)
            }
        }
    }


    private fun filterDataList(position: Int?) {
        initList(entityarr.filter { it.type.toInt() == position }.toTypedArray())
    }

    private fun initList(entityarr: Array<NewsEntity>?) {
        var newsAdapter = NewsAdapter(entityarr!!.toList(), this)
        rv_list.adapter = newsAdapter
        rv_list.layoutManager = LinearLayoutManager(this)
        newsAdapter.setOnClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(newsEntity: NewsEntity) {
                startActivity<NewsListItemActivity>(
//                        "data" to newsEntity
                        Pair("data", newsEntity)
                )
            }

        })
    }

    private fun initData() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)
        var jsonObjectRequest: MyJsonArrayRequest = MyJsonArrayRequest(Constant.NEWS_PAGE_URL,
                Response.Listener {
                    entityarr = Gson().fromJson(it.toString(), Array<NewsEntity>::class.java)
                    filterDataList(type)
                },
                Response.ErrorListener {
                })
        requestQueue.add(jsonObjectRequest)
    }
}
