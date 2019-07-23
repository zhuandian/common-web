package com.zhuandian.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * desc :重写Viewpager，限制翻页效果
 * author：xiedong
 * date：2019/7/23
 */
class NoScrollViewPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {
    private var scrollable: Boolean = false  //是否允许滑动

    fun setScrollable(scrollable: Boolean) {
        this.scrollable = scrollable
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (!scrollable)
            return false
        else
            return super.onTouchEvent(ev)
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (!scrollable)
            return false
        else
            return super.onInterceptTouchEvent(ev)
    }

    //禁止使用滑动动画
    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)
    }
}