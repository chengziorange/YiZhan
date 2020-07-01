package top.orange233.yizhan.module.home.login

import com.orhanobut.logger.Logger
import top.orange233.yizhan.common.repository.UserRepository
import top.orange233.yizhan.util.Preference

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    override fun login(userId: String, password: String) {
        UserRepository.getInstance().login(userId, password)
            .subscribe({
                when (it.status) {
                    200 -> {
                        Preference.instance.putValue(Preference.KEY_IS_LOGGED_IN, true)
                        Preference.instance.putValue(Preference.KEY_PROFILE_CHANGED, true)
                        view.updateProfile()
                    }
                }
            }, {})
    }

    override fun register(email: String, password: String) {
        UserRepository.getInstance().register(email, password)
            .subscribe({
                when (it.status) {
                    201 -> {
                        view.notifyRegisterResult(it.status)
                        register(email, password)
                    }
                }
            }, {
                Logger.d(it.message)
            })
    }

    override fun start() {
        //TODO("Not yet implemented")
    }
}