package com.zhuandian.common.business.fragment

import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.zhuandian.common.utils.Constant
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.entity.Banner
import com.zhuandian.common.entity.HomeEntity
import com.zhuandian.common.entity.HomeList
import com.zhuandian.common.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
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
        var jsonObjectRequest: JsonObjectRequest = JsonObjectRequest(Constant.HOME_PAGE_URL, null,
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

    }

    private fun initBanner(banner: List<Banner>) {
        var urls: MutableList<String> =mutableListOf()
        for (item in banner) {
            urls.add(item.url)
        }
        home_banner.setImages(urls)
        home_banner.setImageLoader(GlideImageLoader())
        home_banner.setIndicatorGravity(BannerConfig.RIGHT)
        home_banner.setDelayTime(5000)
        home_banner.setBannerAnimation(Transformer.CubeIn)
        home_banner.start()
    }
}