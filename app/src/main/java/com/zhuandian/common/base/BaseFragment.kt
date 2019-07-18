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
        setUpView()
        return rootView
    }


    abstract fun getLayoutId(): Int

    abstract fun setUpView()
}



