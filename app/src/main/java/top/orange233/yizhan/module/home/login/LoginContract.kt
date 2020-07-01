package top.orange233.yizhan.module.home.login

import android.content.Context
import top.orange233.yizhan.module.base.BasePresenter

interface LoginContract {
    interface View {
        fun getViewContext(): Context
        fun notifyRegisterResult(status: Int)
        fun updateProfile()
    }

    interface Presenter : BasePresenter {
        fun login(userId: String, password: String)
        fun register(email: String, password: String)
    }
}