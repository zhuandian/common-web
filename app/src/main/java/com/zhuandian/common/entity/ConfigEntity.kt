package com.zhuandian.common.entity

/**
 * desc :
 * author：xiedong
 * date：2019/7/25
 */
data class ConfigEntity(
        var type: Int,// 0 显示原生 ，1 跳转h5
        var url: String
)