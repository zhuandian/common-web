package com.zhuandian.common.business.fragment

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.R
import com.zhuandian.common.adapter.HomeListAdapter
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.HomeListItemDetailActivity
import com.zhuandian.common.entity.Banner
import com.zhuandian.common.entity.HomeEntity
import com.zhuandian.common.entity.HomeList
import com.zhuandian.common.utils.GlideImageLoader
import com.zhuandian.common.utils.MyJsonRequest
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.json.JSONObject

/**
 * desc :项目首页
 * author：xiedong
 * date：2019/7/18
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setUpView() {
        var requestQueue: RequestQueue = Volley.newRequestQueue(activity)
        var jsonObjectRequest: MyJsonRequest = MyJsonRequest(Constant.HOME_PAGE_URL, null,
                Response.Listener<JSONObject> {
                    var homeEntity: HomeEntity = Gson().fromJson(it.toString(), HomeEntity::class.java)
                    initBanner(homeEntity.banner)
                    initList(homeEntity.list)
                },
                Response.ErrorListener {

                })

        requestQueue.add(jsonObjectRequest)
    }

    private fun initList(list: List<HomeList>) {
        var homeListAdapter = HomeListAdapter(list, context)
        homeListAdapter?.setOnClickListener(object : HomeListAdapter.OnItemClickListener {
            override fun onItemClick(homeList: HomeList) {
                (activity as Activity).startActivity<HomeListItemDetailActivity>(
//                        "data" to homeList
                        Pair("data", homeList)
                )
            }
        })
        rv_list.adapter = homeListAdapter
        rv_list.layoutManager = LinearLayoutManager(activity)
    }

    private fun initBanner(banner: List<Banner>) {
        var urls: MutableList<String> = mutableListOf()
        for (item in banner) {
            urls.add(item.url)
        }
        if (urls != null) {
            home_banner.setImages(urls)
            home_banner.setImageLoader(GlideImageLoader())
            home_banner.setIndicatorGravity(BannerConfig.RIGHT)
            home_banner.setDelayTime(5000)
            home_banner.setBannerAnimation(Transformer.CubeIn)
            home_banner.start()
        }
    }
}