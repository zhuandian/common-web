package com.zhuandian.common.business.login

import android.content.ContentValues
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.database.UserDBHelper
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class RegisterActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int = R.layout.activity_register


    override fun setUpView() {
        tiet_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length > 12) {
                    tiet_username.text = Editable.Factory.getInstance().newEditable(s.toString().substring(0, 11))
                    tiet_username.setSelection(11)
                    til_username.isErrorEnabled = true
                    til_username.error = "账号长度最多11个字符"
                } else {
                    til_username.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        tiet_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length < 6) {
                    til_password.error = "密码长度最不能少于6个字符"
                    til_password.isErrorEnabled = true
                } else {
                    til_password.isErrorEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

        btn_login?.setOnClickListener(this)
        tv_register?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_register ->
                ""
            R.id.btn_login ->
                doRegister()
        }
    }

    private fun doRegister() {
        if (!TextUtils.isEmpty(tiet_username.text.toString()) && tiet_password.text.toString()!!.length > 6) {

            insertUser2Db()

            alert("成功", "注册成功!!!") {
                positiveButton("去登陆") {
                    it.dismiss()
                    startActivity<LoginActivity>()
                    finish()
                }
                negativeButton("继续注册") { it.dismiss() }
            }.show()
        } else {
            alert("注册失败", "请完善所有注册信息") {
                yesButton() { it.dismiss() }
            }.show()
        }
    }

    private fun insertUser2Db() {
        var userDBHelper = UserDBHelper(this)
        userDBHelper.writableDatabase
        var contentValues = ContentValues()
        contentValues.put("username", tiet_username.text.toString())
        contentValues.put("password", tiet_password.text.toString())
        userDBHelper.insert(contentValues)
    }
}
