package com.zhuandian.common.business.login


import android.content.Context
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.zhuandian.common.R
import com.zhuandian.common.base.BaseActivity
import com.zhuandian.common.business.MainActivity
import com.zhuandian.common.database.UserDBHelper
import com.zhuandian.common.utils.Constant
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class LoginActivity : BaseActivity(), View.OnClickListener {


    override fun getLayoutId(): Int = R.layout.activity_login

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

        btn_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_register -> {
                startActivity<RegisterActivity>()
                finish()
            }
            R.id.btn_login ->
                doLogin()
        }
    }

    private fun doLogin() {
        if (!TextUtils.isEmpty(tiet_username.text.toString()) && tiet_password.text.toString()!!.length > 6) {
            verifyUser()
        } else {
            alert("登陆失败", "请完善所有信息") {
                yesButton() { it.dismiss() }
            }.show()
        }
    }

    private fun verifyUser() {
        var userInputName = tiet_username.text.toString()
        var userInputPassWord = tiet_password.text.toString()
        var userDBHelper = UserDBHelper(this)
        var writableDatabase = userDBHelper.writableDatabase
        var cursor = writableDatabase.query(UserDBHelper.TABLE_NAME, null, null, null, null, null, null)

        var isAvailable = false
        while (cursor!!.moveToNext()) {
            var name = cursor.getString(cursor.getColumnIndex("username"))
            var password = cursor.getString(cursor.getColumnIndex("password"))
            if (userInputName == name) {
                isAvailable = true
                if (userInputPassWord == password) {
                    //登录成功，下次打开不用重新登录
                    var sharedPreferences = this.getSharedPreferences("config", Context.MODE_PRIVATE)
                    sharedPreferences.edit()
                            .putBoolean("isLogin", true)
                            .commit()
                    indeterminateProgressDialog("登陆中", "请稍后").show()
                    Handler().postDelayed(Runnable {
                        startActivity<MainActivity>()
                        finish()
                    }, 2000)
                } else {
                    alert("密码错误，请重新输入...", "登陆失败") {
                        yesButton() { it.dismiss() }
                    }.show()
                }
                break
            }
        }

        if (!isAvailable) {
            alert("账号不存在，请先注册...", "登陆失败") {
                yesButton() { it.dismiss() }
            }.show()

        }

    }

}
