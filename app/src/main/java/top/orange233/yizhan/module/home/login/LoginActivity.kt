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
            201 -> Snackbar.make(iv_logo, "注册成功", Snackbar.LENGTH_SHORT).show()
            404 -> Snackbar.make(iv_logo, "此邮箱已被注册", Snackbar.LENGTH_SHORT).show()
            500 -> Snackbar.make(iv_logo, "服务器内部错误", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun notifyLoginResult(status: Int) {
        when (status) {
            422, 404 -> Snackbar.make(iv_logo, "用户不存在或密码错误", Snackbar.LENGTH_SHORT).show()
            405 -> Snackbar.make(iv_logo, "此账号已被冻结!", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun updateProfile() {
        finish()
    }
}