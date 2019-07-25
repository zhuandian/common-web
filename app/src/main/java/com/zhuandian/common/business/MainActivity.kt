package com.zhuandian.common.business

import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhuandian.common.R
import com.zhuandian.common.adapter.HomeVpAdapter
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.fragment.HomeFragment
import com.zhuandian.common.business.fragment.NewsFragment
import com.zhuandian.common.business.fragment.PictureFragment
import com.zhuandian.common.business.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun setUpView() {
        var pageList: MutableList<BaseFragment> = mutableListOf()
        pageList.add(HomeFragment())
        pageList.add(NewsFragment())
        pageList.add(PictureFragment())
        pageList.add(SettingFragment())
        vp_main.adapter = HomeVpAdapter(pageList, supportFragmentManager)
        vp_main.currentItem = 0
        vp_main.offscreenPageLimit = 4
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

    private var isCanBack: Boolean = false
    override fun onBackPressed() {
        if (isCanBack) {
            super.onBackPressed()
        } else {
            toast("再按一次退出应用")
            isCanBack = true
            var timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    isCanBack = false
                }
            }, 3000)
        }

    }

}
