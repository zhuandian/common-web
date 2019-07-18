package com.zhuandian.common.entity

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */
 data class HomeEntity(
    val banner: List<Banner>,
    val list: List<HomeList>
)

data class HomeList(
    val content: String,
    val imgUrl: String,
    val time: String,
    val title: String
)

data class Banner(
    val url: String
)