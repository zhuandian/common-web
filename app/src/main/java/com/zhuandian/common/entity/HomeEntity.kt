package com.zhuandian.common.entity

import java.io.Serializable

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */

data class HomeEntity(
    val banner: List<Banner>,
    val list: List<HomeList>
):Serializable

data class HomeList(
    val content: String,
    val imgUrl: String,
    val time: String,
    val title: String
):Serializable

data class Banner(
    val url: String
):Serializable