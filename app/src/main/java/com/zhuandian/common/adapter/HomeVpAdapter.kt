package com.zhuandian.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhuandian.common.base.BaseFragment

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */
class HomeVpAdapter(val fragmentList: List<BaseFragment>, var fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): BaseFragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}