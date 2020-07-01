package top.orange233.yizhan.module.home.login

import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.gyf.immersionbar.ktx.immersionBar
import kotlinx.android.synthetic.main.activity_login.*
import top.orange233.yizhan.R
import top.orange233.yizhan.module.base.BaseActivity

class LoginActivity : BaseActivity(), LoginContract.View {
    private lateinit var presenter: LoginPresenter

    override fun getLayout(): Int = R.layout.activity_login

    override fun initView() {
        immersionBar {
            fitsSystemWindows(true)
            statusBarDarkFont(false)
        }

        presenter = LoginPresenter(this)
        presenter.start()
    }

    override fun initEvent() {
        toolbar.setNavigationOnClickListener { finish() }

        login_button.setOnClickListener {
            val userId = login_username_edittext.text.toString()
            val password = login_password_edittext.text.toString()
            presenter.login(userId, password)
        }

        register_button.setOnClickListener {
            val email = login_username_edittext.text.toString()
            val password = login_password_edittext.text.toString()
            presenter.register(email, password)
        }
    }

    override fun getViewContext(): Context = this

    override fun notifyRegisterResult(status: Int) {
        when (status) {
            201 -> Snackbar.make(iv_logo, "注册成功", Snackbar.LENGTH_SHORT)
        }
    }

    override fun updateProfile() {
        finish()
    }
}