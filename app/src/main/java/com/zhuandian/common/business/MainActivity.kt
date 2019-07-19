package com.zhuandian.common.business

import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhuandian.common.R
import com.zhuandian.common.adapter.HomeVpAdapter
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.fragment.HomeFragment
import com.zhuandian.common.business.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setUpView() {
        var pageList: MutableList<BaseFragment> = mutableListOf()
        pageList.add(HomeFragment())
        pageList.add(HomeFragment())
        pageList.add(HomeFragment())
        pageList.add(SettingFragment())
        vp_main.adapter = HomeVpAdapter(pageList, supportFragmentManager)
        vp_main.currentItem = 0
        initBottomTab()
    }

    private fun initBottomTab() {
        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    vp_main.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    vp_main.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    vp_main.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_setting -> {
                    vp_main.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                nav_view.menu.getItem(position).setChecked(true)
            }

        })


    }


}