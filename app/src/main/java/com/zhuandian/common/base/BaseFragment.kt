package com.zhuandian.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */

abstract class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView: View = inflater.inflate(getLayoutId(), container, false);
        return rootView
    }

    /**
     * onCreateView中绑定setUpView会因为控件为完成初始化导致空指针，
     * 为了安全，建议在onViewCreated绑定setUpView抽象方法
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpView()
}



