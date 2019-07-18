package com.zhuandian.common.business

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhuandian.common.R
import com.zhuandian.common.adapter.HomeVpAdapter
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.base.BaseFragment
import com.zhuandian.common.business.fragment.HomeFragment
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
        vp_main.adapter = HomeVpAdapter(pageList, supportFragmentManager)
        vp_main.currentItem = 0
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}
