package com.zhuandian.common.base

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.zhuandian.common.R
import kotlinx.android.synthetic.main.layout_common_title.view.*

/**
 * desc :
 * author：xiedong
 * date：2019/7/18
 */

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    /**
     * 为了确保安全，在onStart绑定setUpView
     */
    override fun onStart() {
        super.onStart()
        setUpView()
        findViewById<ImageView>(R.id.iv_back)?.setOnClickListener { this.finish() }
    }

    abstract fun getLayoutId(): Int

    abstract fun setUpView()
}