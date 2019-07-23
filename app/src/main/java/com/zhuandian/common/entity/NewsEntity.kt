package com.zhuandian.common.entity

import java.io.Serializable

/**
 * desc :
 * author：xiedong
 * date：2019/7/23
 */
data class NewsEntity(
        val content: String,
        val imgUrl: String,
        val time: String,
        val title: String,
        val type: String  //1.热搜 2.便宜购物 3.促销  4网红推荐
) : Serializable